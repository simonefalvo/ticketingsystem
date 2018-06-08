package it.uniroma2.ticketingsystem.logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.uniroma2.ticketingsystem.controller.ReflectionController;

public class ObjSer {

    private ObjectMapper objectMapper;

    private ReflectionController reflectionController;

    public ObjSer(){

        this.objectMapper = new ObjectMapper();

    }

    public String objToJson(Object object){

        String jsonString=null;

        try {

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            objectMapper.setDateFormat(sdf);

            //  mapper.writeValue(System.out, object);

            jsonString = objectMapper.writeValueAsString(object);

        }
        catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.print("----------------");
        System.out.println(jsonString);

        return jsonString;

    }

    public String buildJson(Object object, List<String> attributes) throws NoSuchFieldException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");

        stringBuilder.append(" \"id\" : " + reflectionController.getField(object,"id") + ", ");



        int len = attributes.size();
        int i=0;
        while(i<0){
            String temp_attr = attributes.get(i);

            String attr_value = reflectionController.getField(object,temp_attr);

            //suddividere casi stringa e int
            stringBuilder.append("\"" + temp_attr + "\": "+ attr_value +", ");

        }

        stringBuilder.append(" }");
        String jsonString = stringBuilder.toString();
        return jsonString;

    }

}
