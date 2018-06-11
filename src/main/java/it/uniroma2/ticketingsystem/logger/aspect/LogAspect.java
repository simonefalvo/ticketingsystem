package it.uniroma2.ticketingsystem.logger.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.uniroma2.ticketingsystem.controller.ReflectionController;
import it.uniroma2.ticketingsystem.logger.ObjSer;
import it.uniroma2.ticketingsystem.logger.Record;
import it.uniroma2.ticketingsystem.logger.RecordController;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
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

    private Object getMethodParameter(String objectName, MethodSignature signature, Object[] jpArgs){
        String[] argNames = signature.getParameterNames();
        //Object[] args = jp.getArgs();

        int index = 0;
        for (String s : argNames) {
            System.out.println("**** argomento " + s);
            if (objectName.equals(s)) {
                System.out.println("\n\n\n**** argomento TROVATO " + s + ", index: " + String.valueOf(index));
                break;
            }
            index++;
        }

        Object target = jpArgs[index];
        return target;
    }

    @After("@annotation(LogOperation)")
    public void logOperationFlow(JoinPoint jp) throws Throwable {

        //prendo firma del metodo annotato
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        LogOperation a = method.getAnnotation(LogOperation.class);
        String value = a.objName();
        String objectName = a.objName();


        //controllo che il parametro sia stato valorizzato
        if(value.equals("")){//voglio serializzare solo il nome del metodo senza parametri
            System.out.println("******** voglio serializzare solo il nome del metodo senza parametri *******");
            String methodName = signature.getName();
            Record rec = new Record(methodName, null);
            recordController.createRecord(rec);

        }
        else{//voglio serializzare un oggetto specifico passato come parametro del metodo
            System.out.println("********* voglio serializzare un oggetto specifico passato come parametro del metodo");
            //estraggo l'oggetto di interesse da serializzare
            Object target = getMethodParameter(objectName, signature, jp.getArgs());

            //analizzo i parametri di interesse della classe dell'oggetto da serializzare

            String[] param = getParameters(target);
            if(param == null){
                //voglio serializzare tutti i parametri dell oggetto

                System.out.println("********* voglio serializzare tutti i parametri dell oggetto******");
                String methodName = signature.getName();
                Field field = FieldUtils.getField(target.getClass(), "id", true);
                Integer objectId = (Integer) field.get(target);

                //serializzo il singolo oggetto

                System.out.println("\n\n\n JSON del singolo oggetto: "+ObjSer.objToJson(target));
                Record rec = new Record(methodName, null, "class",objectId , ObjSer.objToJson(target) );

                recordController.createRecord(rec);

            }
            else{
                //voglio serializzare solo alcuni attributi dell'oggetto
                System.out.println("********* voglio serializzare alcuni parametri dell oggetto******");
                System.out.println("\n\n\n**** LogAspect.logOperation() " + target.toString());

                String jsonString = buildJson(target,param);
                //todo: bisogna rendere l'id personalizzabile
                Field field = FieldUtils.getField(target.getClass(), "id", true);
                Integer objectId = (Integer) field.get(target);
                String methodName = signature.getName();
                Record rec = new Record(methodName, null, "class",objectId,jsonString);

                recordController.createRecord(rec);
            }


        }







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
                params = myAnnotation.logAttrs();
                System.out.println("Parmas = "+params.toString());
                if(params.length==1 && params[0].equals("")) {
                    System.out.println("Array vuoto \t lunghezza = " + params.length);
                    return null;
                }
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