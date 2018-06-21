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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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
        //acquisisco l'elenco delle variabili da serializzare
        String[] objectName = annotation.objName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String author = auth.getName();


        // controllo che il parametro sia stato valorizzato
        if (objectName[0].equals("")) { // voglio serializzare solo il nome del metodo senza parametri
            String methodName = signature.getName();
            Record record = new Record(methodName, null);
            recordController.createRecord(record);
        }
        else{ // voglio serializzare un oggetto specifico passato come parametro del metodo
            // estraggo l'oggetto di interesse da serializzare
            String methodName = signature.getName();
            //itero per ogni elemento nella lista @objectName
            Object[] targetObject = new Object[objectName.length];
            String[] objectId = new String[objectName.length];
            String[] serializedObject = new String[objectName.length];

            for (int i=0; i<objectName.length;++i){
                targetObject[i] = ReflectUtils.getMethodParameter(objectName[i], signature, jp.getArgs());

                // analizzo i parametri di interesse della classe dell'oggetto da serializzare
                String[] params = ReflectUtils.getParameters(targetObject[i]);
                String[] idParams = ReflectUtils.getIDParameters(targetObject[i]);
                objectId[i] = ObjSer.buildIDJson(targetObject, idParams);

                if (params == null)
                    // serializza tutti i parametri dell oggetto
                    serializedObject[i] = ObjSer.objToJson(targetObject);
                else
                    // serializza solo alcuni attributi dell'oggetto
                    serializedObject[i] = ObjSer.buildJson(targetObject, params);

            }

            String mergedJson = ObjSer.objectsToJson(serializedObject,objectName);

            System.out.println("*************************\n" + mergedJson + "\n ***************************");

            Record record = new Record(methodName, author, targetObject.getClass().getSimpleName(), mergedJson);
            recordController.createRecord(record);
        }
        
    }

}