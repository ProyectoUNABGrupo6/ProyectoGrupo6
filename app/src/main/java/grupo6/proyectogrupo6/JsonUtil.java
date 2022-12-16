package grupo6.proyectogrupo6;

import androidx.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class JsonUtil {

    @Nullable
    public static <O> String toJson(O object){
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        mapper.setDateFormat(df);
        try {
            return  mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    @Nullable
    public static <O> O toObject(String json, Class<O> type){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        mapper.setDateFormat(df);
        try {
            return  mapper.readValue(json,type);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    @Nullable
    public static Map<String,String> toMap(String json){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        mapper.setDateFormat(df);
        try {
            return mapper.readValue(json,new TypeReference<Map<String,String>>(){});
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
