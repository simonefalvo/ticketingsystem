package it.uniroma2.ticketingsystem.logger.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @After("@annotation(LogOperation)")
    public void logExecutionTime(JoinPoint jp) throws Throwable {
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
        getParameters(target);

        System.out.println("\n\n\n**** LogAspect.logOperation() " + target.toString());
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
}