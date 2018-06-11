package it.uniroma2.ticketingsystem.logger.aspect;

import it.uniroma2.ticketingsystem.logger.utils.ObjSer;
import it.uniroma2.ticketingsystem.logger.Record;
import it.uniroma2.ticketingsystem.logger.RecordController;
import it.uniroma2.ticketingsystem.logger.utils.ReflectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@Aspect
@Component
public class LogAspect {

    @Autowired
    private RecordController recordController;

    @After("@annotation(LogOperation)")
    public void logOperationFlow(JoinPoint jp) throws Throwable {

        // prendo firma del metodo annotato
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        LogOperation annotation = method.getAnnotation(LogOperation.class);
        String objectName = annotation.objName();


        // controllo che il parametro sia stato valorizzato
        if (objectName.equals("")) { // voglio serializzare solo il nome del metodo senza parametri
            System.out.println("******** voglio serializzare solo il nome del metodo senza parametri *******");
            String methodName = signature.getName();
            Record record = new Record(methodName, null);
            recordController.createRecord(record);
        }
        else{ // voglio serializzare un oggetto specifico passato come parametro del metodo
            System.out.println("********* voglio serializzare un oggetto specifico passato come parametro del metodo");
            // estraggo l'oggetto di interesse da serializzare
            Object targetObject = ReflectUtils.getMethodParameter(objectName, signature, jp.getArgs());

            // analizzo i parametri di interesse della classe dell'oggetto da serializzare
            String[] params = getParameters(targetObject);
            String methodName = signature.getName();
            // TODO: sostituire (non è detto che l'identificativo sia un intero e che si chiami id)
            Integer objectId = ReflectUtils.fieldToInteger(targetObject, "id");
            String serializedObject;

            if (params == null) {
                // voglio serializzare tutti i parametri dell oggetto
                System.out.println("********* voglio serializzare tutti i parametri dell oggetto******");
                //serializzo il singolo oggetto
                serializedObject = ObjSer.objToJson(targetObject);
                System.out.println("\n\n\n JSON del singolo oggetto: " + serializedObject);
            }
            else{
                //voglio serializzare solo alcuni attributi dell'oggetto
                System.out.println("********* voglio serializzare alcuni parametri dell oggetto******");
                System.out.println("\n\n\n**** LogAspect.logOperation() " + targetObject.toString());
                serializedObject = ObjSer.buildJson(targetObject, params);
            }

            Record record = new Record(methodName, null, "class", objectId, serializedObject);
            recordController.createRecord(record);
        }
        
    }

    //controllare se la classe dell'oggetto è annotata
    //se si, controllare se ha inserito dei parametri rilevanti
    //restituire i parametri rilevanti
    private String[] getParameters(Object targetObject){
        String[]params = null;
        //analizzo tutte le annotazioni della classe dell'oggetto
        for (Annotation annotation : targetObject.getClass().getAnnotations()){

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


}