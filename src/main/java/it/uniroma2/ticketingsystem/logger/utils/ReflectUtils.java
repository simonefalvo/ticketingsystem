package it.uniroma2.ticketingsystem.logger.utils;

import it.uniroma2.ticketingsystem.logger.exception.ObjNotFoundException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;

public abstract class ReflectUtils {


    public static String fieldToString(Object instance, String fieldName) {

        String result = null;

        try {
            Field field = FieldUtils.getField(instance.getClass(), fieldName, true);
            result = (String) field.get(instance);
        } catch (IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }

        if (result == null)
            //throw new AttrNotFoundException("Attributo \"" + fieldName + "\" non trovato nella classe \"" + instance.getClass().getName() + "\"");
            System.err.println("Attributo \"" + fieldName + "\" non trovato nella classe \"" + instance.getClass().getName() + "\"");
        return result;
    }

    public static Integer fieldToInteger(Object instance, String fieldName) {

        Integer result = null;

        try {
            Field field = FieldUtils.getField(instance.getClass(), fieldName, true);
            result = (Integer) field.get(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Object getMethodParameter(String objectName, MethodSignature signature, Object[] args)
            throws ObjNotFoundException {

        String[] argNames = signature.getParameterNames();

        int index = 0;
        boolean found = false;
        for (String s : argNames) {
            if (objectName.equals(s)) {
                found = true;
                break;
            }
            index++;
        }

        if (!found)
            throw new ObjNotFoundException(
                    "Oggetto \"" + objectName + "\" non trovato nella lista dei parametri del metodo");

        return args[index];
    }
}

