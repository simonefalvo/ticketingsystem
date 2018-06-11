package it.uniroma2.ticketingsystem.logger.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


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


    public static String buildJson(Object object, String[] attributes) {

        // TODO: sostituire (non è detto che l'identificativo sia un intero e che si chiami id)
        Integer id = ReflectUtils.fieldToInteger(object, "id");

        String t, st = "{ \"id\": \"" + id +"\", ";

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

}