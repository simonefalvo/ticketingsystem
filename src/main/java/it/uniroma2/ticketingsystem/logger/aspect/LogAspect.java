package it.uniroma2.ticketingsystem.logger.aspect;

import it.uniroma2.ticketingsystem.logger.entity.Payload;
import it.uniroma2.ticketingsystem.logger.utils.AspectUtils;
import it.uniroma2.ticketingsystem.logger.utils.ObjSer;
import it.uniroma2.ticketingsystem.logger.entity.Record;
import it.uniroma2.ticketingsystem.logger.RecordController;
import it.uniroma2.ticketingsystem.logger.utils.ReflectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;


@Aspect
@Component
public class LogAspect {

    @Autowired
    private RecordController recordController;


    @Around("@annotation(LogOperation)")
    public void logOperationAdvice(ProceedingJoinPoint jp) throws Throwable {

        // run annotated method
        Object returnObject = jp.proceed();

        // get method annotation
        MethodSignature signature = (MethodSignature) jp.getSignature();
        LogOperation annotation = signature.getMethod().getAnnotation(LogOperation.class);

        //get annotation options
        String[] inputArgsNames = annotation.inputArgs();
        boolean returnObjectName = annotation.returnObject();
        String opName = annotation.opName();
        String tag = annotation.tag();

        Record record;

        Payload[] payloads = new Payload[inputArgsNames.length+1];
        String serializedReturnObject = "";
        Payload returnPayload;

        // check options and do related stuff
        if (AspectUtils.defaultOption(LogOperation.class, "opName", opName))
            opName = signature.getName();

        //create record object
        record = new Record(opName,null, tag);


        if (returnObjectName)
            serializedReturnObject = serializeObject(returnObject);

        //voglio serializzare i parametri in input
        if (!AspectUtils.defaultOption(LogOperation.class, "inputArgs", inputArgsNames)) {

            Object[] inputArgs = new Object[inputArgsNames.length];
            String[] serializedObject = new String[inputArgsNames.length];

            for (int i = 0; i < inputArgsNames.length; ++i) {
                inputArgs[i] = ReflectUtils.getMethodParameter(inputArgsNames[i], signature, jp.getArgs());
                serializedObject[i] = serializeObject(inputArgs[i]);
                payloads[i] = new Payload(serializedObject[i],"tipo",record);
            }
        }

        record.setPayloads(new HashSet<>(Arrays.asList(payloads)));

        recordController.createRecord(record);
    }





    private static String serializeObject(Object object) throws Throwable{

        String[] params = ReflectUtils.getParameters(object);
        String[] idParams = ReflectUtils.getIDParameters(object);

        String objectId ="";

        String serializedObject;

        if(params == null){
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