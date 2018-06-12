package it.uniroma2.ticketingsystem.logger.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.reflect.FieldUtils;


public abstract class ObjSer {


    public static String objToJson(Object object){

        String jsonString=null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            objectMapper.setDateFormat(sdf);

            //  mapper.writeValue(System.out, object);

            jsonString = objectMapper.writeValueAsString(object);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
        System.out.print("----------------");
        System.out.println(jsonString);
        */
        return jsonString;

    }


    public static String buildJson(Object object, String[] attributes) throws Throwable {

        String[] idParams = ReflectUtils.getIDParameters(object);
        String id = getIDJsonString(object, idParams);

        String t, st = "{ " + id + " , ";

        int i , l = attributes.length;

        for (i = 0; i < l - 1; i++) {
            t = "\"" + attributes[i] + "\": \"" + ReflectUtils.fieldToString(object, attributes[i]) + "\", ";
            st = st.concat(t);
        }
        t = "\"" + attributes[i] + "\": \"" + ReflectUtils.fieldToString(object, attributes[i]) + "\" }";
        st = st.concat(t);
        // System.out.println("\n\n*******" + st + "*******");

        return st;
    }



    public static String buildIDJson(Object object, String[] attributes) throws Throwable{

        String st="{ ";

        String s = getIDJsonString(object,attributes);

        st = st.concat(s);
        st = st.concat(" }");
        return st;

    }


    public static String getIDJsonString(Object object, String[] attributes) throws Throwable {

        String t, st = "";

        int l = attributes.length;
        int i = 0;

        while (i < l - 1) {
            Field field = FieldUtils.getField(object.getClass(), attributes[i], true);
            // TODO: check su tipo dell'oggetto
            t = "\"" + attributes[i] + "\": \"" + field.get(object) + "\", ";
            st = st.concat(t);
            i++;
        }

        Field field_attr = FieldUtils.getField(object.getClass(), attributes[i], true);
        t = "\"" + attributes[i] + "\": \"" + field_attr.get(object) + "\"";

        //System.out.print("\n\n\n ATTR:"+t+"\n\n\n");

        st = st.concat(t);

        return st;
    }
}