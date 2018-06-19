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

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;


@Aspect
@Component
public class LogAspect {

    @Autowired
    private RecordController recordController;


    private static Boolean optionInputArgsIsSet(MethodSignature signature){
        LogOperation annotation = signature.getMethod().getAnnotation(LogOperation.class);
        String defaultValue;
        Boolean result=false;
        try {
            // todo
            /*defaultValue =*/  LogOperation.class.getDeclaredMethod("inputArgs").getDefaultValue();
            defaultValue = "";
            if(annotation.inputArgs().equals(defaultValue))
                result = false;
            else
                result = true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getOperationName(MethodSignature signature){
        LogOperation annotation = signature.getMethod().getAnnotation(LogOperation.class);
        String defaultValue;
        String methodName= "";

        try {
            //todo
            /*defaultValue =(String) */LogOperation.class.getDeclaredMethod("opName").getDefaultValue();
            defaultValue = "";
            String opName = annotation.opName();

            methodName = opName.equals(defaultValue) ? signature.getName() : opName;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("getOperationName methodName = "+methodName);
        return methodName;
    }

    private static String getSerializedReturnObject (MethodSignature signature, Object returnObject){
        LogOperation annotation = signature.getMethod().getAnnotation(LogOperation.class);
        String returnObj = annotation.returnObject();
        String serializedReturnObject="";
        String defaultValue;
        try {
            //todo
            /*defaultValue = (String) */LogOperation.class.getDeclaredMethod("returnObject").getDefaultValue();
            defaultValue = "false";
            if(!returnObj.equals(defaultValue)) {
                serializedReturnObject = serializeObject(returnObject);
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("getSerializedReturnObject serializedReturnObject = "+serializedReturnObject);
        return serializedReturnObject;

    }

    private boolean defaultOption(Annotation annotation, Object option, String optionName) {
        Object defaultValue = null;
        try {
            defaultValue = annotation.getClass().getDeclaredMethod(optionName).getDefaultValue();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return option.equals(defaultValue);
    }


    @Around("@annotation(LogOperation)")
    public void logOperationAdvice(ProceedingJoinPoint jp) throws Throwable {

        // run annotated method
        Object returnObject = jp.proceed();

        // get method annotation
        MethodSignature signature = (MethodSignature) jp.getSignature();
        LogOperation annotation = signature.getMethod().getAnnotation(LogOperation.class);

        String methodName = getOperationName(signature);

        //get annotation options
        String[] inputArgsNames = annotation.inputArgs();
        String returnObjectName = annotation.returnObject();

        Record record;

        if (defaultOption(annotation, returnObjectName, "returnObject"))
            System.err.println("opzione return object DEFAULT");
        else
            System.err.println("opzione return object NON DEFAULT");

        //if serialized object is set true get it in json
        String serializedReturnObject = getSerializedReturnObject(signature, returnObject);


        if (!optionInputArgsIsSet(signature)) {
            // non voglio serializzare oggetti in input
            record = new Record(methodName, null, serializedReturnObject);
        }
        else{
            // voglio serializzare uno o più oggetti passato come parametri del metodo
            // estraggo gli oggetti di interesse da serializzare

            //itero per ogni elemento nella lista @inputArgsNames
            Object[] targetObject = new Object[inputArgsNames.length];
            String[] serializedObject = new String[inputArgsNames.length];

            System.out.print("\n\n "+ inputArgsNames);

            for (int i=0; i<inputArgsNames.length;++i){
                targetObject[i] = ReflectUtils.getMethodParameter(inputArgsNames[i], signature, jp.getArgs());

                // analizzo i parametri di interesse della classe dell'oggetto da serializzare
                String[] params = ReflectUtils.getParameters(targetObject[i]);

                if (params == null) {
                    // serializza tutti i parametri dell oggetto
                    serializedObject[i] = ObjSer.objToJson(targetObject[i]);
                }else {
                    // serializza solo alcuni attributi dell'oggetto
                    System.out.print("\n tagetObj = "+targetObject+" \t params = "+params.toString());
                    serializedObject[i] = ObjSer.buildJson(targetObject[i], params);
                }

            }

            String mergedJson = ObjSer.objectsToJson(serializedObject,inputArgsNames);

            record = new Record(methodName, null, targetObject.getClass().getSimpleName(), mergedJson, serializedReturnObject);

        }

        recordController.createRecord(record);



    }



    private static String serializeObject(Object object) throws Throwable{

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