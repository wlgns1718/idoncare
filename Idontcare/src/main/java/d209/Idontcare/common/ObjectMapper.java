package d209.Idontcare.common;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class ObjectMapper {
    public static Map<String, String> findErrorCode(String error) throws Exception{
        String response = error.substring(7, error.length()-1);
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        Map<String, String> map = new HashMap<>();
        map.put("code", jsonNode.get("code").asText());
        map.put("error", jsonNode.get("msg").asText());
        return map;
    }
}
