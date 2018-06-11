package it.uniroma2.ticketingsystem.logger.aspect;

import it.uniroma2.ticketingsystem.controller.ReflectionController;
import it.uniroma2.ticketingsystem.logger.ObjSer;
import it.uniroma2.ticketingsystem.logger.Record;
import it.uniroma2.ticketingsystem.logger.RecordController;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private RecordController recordController;

    private ObjSer objSer;
    private ReflectionController reflectionController;


    @After("@annotation(LogOperation)")
    public void logExecutionTime(JoinPoint jp) throws Throwable {

        String methodName = jp.getSignature().getName();

        //prendo firma del metodo annotato
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        LogOperation a = method.getAnnotation(LogOperation.class);
        String value = a.objName();
        String[] argNames = signature.getParameterNames();
        Object[] args = jp.getArgs();

        int index = 0;
        for (String s : argNames) {
            System.out.println("**** argomento " + s);
            if (value.equals(s)) {
                System.out.println("\n\n\n**** argomento TROVATO " + s + ", index: " + String.valueOf(index));
                break;
            }
            index++;
        }

        Object target = args[index];
        String[] param = getParameters(target);

        System.out.println("\n\n\n**** LogAspect.logOperation() " + target.toString());

        String jsonString = buildJson(target,param);

        Field field = FieldUtils.getField(target.getClass(), "id", true);
        Integer objectId = (Integer) field.get(target);

        Record rec = new Record(methodName, null, "class",objectId,jsonString);

        recordController.createRecord(rec);

    }

    //controllare se la classe dell'oggetto è annotata
    //se si, controllare se ha inserito dei parametri rilevanti
    //restituire i parametri rilevanti
    private String[] getParameters(Object target){
        String[]params = null;
        //analizzo tutte le annotazioni della classe dell'oggetto
        for (Annotation annotation : target.getClass().getAnnotations()){

            System.out.println("\n\n\n annotazione = "+annotation.toString());
            if(annotation.annotationType().equals(LogClass.class)) {
                System.out.println("\n\n\nClasse annotata con LogClass");
                LogClass myAnnotation = (LogClass) annotation;
                params = ((LogClass) annotation).logAttrs();
                for(String s: params)
                    System.out.println("Array = "+s);

                break;
            }
        }

        return params;

    }

    public String buildJson(Object object, String[] attributes) throws Throwable {

        Field field = FieldUtils.getField(object.getClass(), "id", true);
        Integer result = (Integer) field.get(object);

        String st = "{ id: " + result +", ";

        int l = attributes.length;
        int i=0;

        while(i<l){
            String t = "\""+attributes[i]+"\": \""+ reflectionController.getField(object,attributes[i])+"\", ";
            st =st.concat(t);
            i++;
        }

        st =st.concat(" }");

        System.out.println("\n\n*******"+st+"*******");

        return st;

    }
}