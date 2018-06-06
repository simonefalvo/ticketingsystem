package it.uniroma2.ticketingsystem.controller;

import org.apache.tomcat.util.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.validator.constraints.URL;


import java.lang.reflect.Field;

public class ReflectionController {

    //call as getField(istanceOfAClass casted as Object, nameOfAttributeToKnow)
    public static String getField(Object myIstance, String fieldName){
        String result = null;
        try {
            Field field = FieldUtils.getField(myIstance.getClass(), fieldName, true);
            result = (String) field.get(myIstance);
        } catch (Exception e) {
            //System.err.print(ExceptionUtils.getStackTrace(e));
            System.err.print(e.getStackTrace());
        }
        System.out.println("Using Reflection Utils "+fieldName+" : " + result);
        Field[] myField = FieldUtils.getAllFields(myIstance.getClass());
        for(int i=0; i<myField.length; i++){
            System.out.println("\n Field "+i+" = "+myField[i]);
        }

        return result;

    }
}
