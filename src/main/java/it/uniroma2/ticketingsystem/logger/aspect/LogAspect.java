package it.uniroma2.ticketingsystem.logger.aspect;

import it.uniroma2.ticketingsystem.logger.utils.ObjSer;
import it.uniroma2.ticketingsystem.logger.Record;
import it.uniroma2.ticketingsystem.logger.RecordController;
import it.uniroma2.ticketingsystem.logger.utils.ReflectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class LogAspect {

    @Autowired
    private RecordController recordController;

    @Around("@annotation(LogOperation)")
    public void logOperationFlow(ProceedingJoinPoint jp) throws Throwable {

        Object returnObject = jp.proceed();

        // prendo firma del metodo annotato
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        LogOperation annotation = method.getAnnotation(LogOperation.class);
        String[] objectName= annotation.objName();

        String returnObj = annotation.returnObject();
        Record record;

        String serializedReturnObject="";


        if(returnObj.equals("true")){
            //serializzazione del return object

            serializedReturnObject = serializeObject(returnObject);

        }


        // controllo che il parametro sia stato valorizzato
        if (objectName.equals("")) { // voglio serializzare solo il nome del metodo senza parametri

            String methodName = signature.getName();
            record = new Record(methodName, null,serializedReturnObject);


        }
        else{ // voglio serializzare un oggetto specifico passato come parametro del metodo
            // estraggo l'oggetto di interesse da serializzare


            String methodName = signature.getName();
            //itero per ogni elemento nella lista @objectName
            Object[] targetObject = new Object[objectName.length];
            String[] serializedObject = new String[objectName.length];

            System.out.print("\n\n "+ objectName);

            for (int i=0; i<objectName.length;++i){
                targetObject[i] = ReflectUtils.getMethodParameter(objectName[i], signature, jp.getArgs());

                // analizzo i parametri di interesse della classe dell'oggetto da serializzare
                String[] params = ReflectUtils.getParameters(targetObject[i]);



                //String[] idParams = ReflectUtils.getIDParameters(targetObject[i]);

                if (params == null) {
                    // serializza tutti i parametri dell oggetto
                    serializedObject[i] = ObjSer.objToJson(targetObject[i]);
                }else {
                    // serializza solo alcuni attributi dell'oggetto
                    System.out.print("\n tagetObj = "+targetObject+" \t params = "+params.toString());
                    serializedObject[i] = ObjSer.buildJson(targetObject[i], params);
                }

            }

            String mergedJson = ObjSer.objectsToJson(serializedObject,objectName);

            System.out.println("*************************\n" + mergedJson + "\n ***************************");
/*


            Object targetObject = ReflectUtils.getMethodParameter(objectName, signature, jp.getArgs());

            System.out.print("object: "+ targetObject.toString());
            System.out.print("object class: "+ targetObject.getClass().toString());

            // analizzo i parametri di interesse della classe dell'oggetto da serializzare
            //String[] params = ReflectUtils.getParameters(targetObject);
            String methodName = signature.getName();
            //String[] idParams = ReflectUtils.getIDParameters(targetObject);
            //TODO: se idParams è nullo, nuovo flusso (es Integer)
            String serializedObject;

            serializedObject = serializeObject(targetObject);

            record = new Record(methodName, null, targetObject.getClass().getSimpleName(), serializedObject,serializedReturnObject);
*/
            record = new Record(methodName, null, targetObject.getClass().getSimpleName(), mergedJson, serializedReturnObject);


        }

        recordController.createRecord(record);



    }

    private String serializeObject(Object object) throws Throwable{

        String[] params = ReflectUtils.getParameters(object);
        String[] idParams = ReflectUtils.getIDParameters(object);

        String objectId ="";

        String serializedObject;


        if(params ==null){
            // serializza tutti i parametri dell oggetto
            if(idParams==null){
                objectId = "no id";
                serializedObject = ObjSer.objToJson(object);

            }else{
                objectId = ObjSer.buildIDJson(object, idParams);
                serializedObject = ObjSer.objToJson(object);

            }


        }else{
            // serializza solo alcuni attributi dell'oggetto
            objectId = ObjSer.buildIDJson(object, idParams);
            serializedObject = ObjSer.buildJson(object, params);
        }

        return serializedObject;


    }




}