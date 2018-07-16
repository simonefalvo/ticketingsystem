package it.uniroma2.ticketingsystem.logger.aspect;

import it.uniroma2.ticketingsystem.logger.controller.RecordControllerMongo;
import it.uniroma2.ticketingsystem.logger.entity.mongo.Payload;
import it.uniroma2.ticketingsystem.logger.entity.mongo.Record;
import it.uniroma2.ticketingsystem.logger.exception.ObjNotFoundException;
import it.uniroma2.ticketingsystem.logger.utils.AspectUtils;
import it.uniroma2.ticketingsystem.logger.utils.ObjSer;
import it.uniroma2.ticketingsystem.logger.utils.ReflectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;


@Aspect
@Component
@Configuration
@Profile("mongo")
public class LogAspectMongo {

    @Autowired
    private RecordControllerMongo recordController;

    @Around("@annotation(LogOperation)")
    public Object logOperationAdvice(ProceedingJoinPoint jp) throws Throwable {

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

        //Get author name
        String author = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null)
            author = auth.getName();


        Payload[] payloads;// = new Payload[inputArgsNames.length + 1];//dim = argumens +1 (including return object)
        if(returnObjectName)
            payloads = new Payload[inputArgsNames.length + 1];//dim = argumens +1 (including return object)
        else
            payloads = new Payload[inputArgsNames.length];//dim = argumens +1 (including return object)


        String serializedReturnObject = "";

        // check options and do related stuff
        if (AspectUtils.defaultOption(LogOperation.class, "opName", opName))
            opName = signature.getName();



        if (returnObjectName) {
            try {
                serializedReturnObject = ObjSer.serializeObject(returnObject);
                String idJSON = ObjSer.buildIDJson(returnObject, ReflectUtils.getIDParameters(returnObject));
                payloads[payloads.length-1] = new Payload(serializedReturnObject, idJSON,"output", returnObject.getClass().getSimpleName());
            }
            catch (NullPointerException e){
                System.out.println("Attention: Return Object is null!");
                payloads[payloads.length-1] = new Payload("null", "null","output", "null");
            }

        }

        //voglio serializzare i parametri in input
        //if (!AspectUtils.defaultOption(LogOperation.class, "inputArgs", inputArgsNames)) {
        if (!inputArgsNames[0].equals("") ) {

            Object[] inputArgs = new Object[inputArgsNames.length];
            String[] serializedObject = new String[inputArgsNames.length];

            for (int i = 0; i < inputArgsNames.length; ++i) {
                //inputArgs[i] = oggetto da serializzare
                try {
                    inputArgs[i] = ReflectUtils.getMethodParameter(inputArgsNames[i], signature, jp.getArgs());
                    //oggetto Serializzato
                    serializedObject[i] = ObjSer.serializeObject(inputArgs[i]);
                    //id dell'oggetto serializzato
                    String idJSON = ObjSer.buildIDJson(inputArgs[i], ReflectUtils.getIDParameters(inputArgs[i]));

                    payloads[i] = new Payload(serializedObject[i], idJSON,"input",inputArgs[i].getClass().getSimpleName());
                }
                catch (ObjNotFoundException e){
                    System.out.println("Object name may be incorrect "+inputArgsNames[i]);
                }

            }
        }

        //create record object
        Record record = new Record(opName, author, tag, new HashSet<>(Arrays.asList(payloads)));

        recordController.createRecord(record);
        return returnObject;

    }


}