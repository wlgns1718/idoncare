package d209.Idontcare.common.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.Map;

@Data
public class APIResultDto {
  HttpStatus status;
  Map<String, String> header;
  Object body;
  
  public <T> T getHeader(Class<T> clazz){
    Gson gson = new Gson();
    JsonElement jsonElement = gson.toJsonTree(header);
    
    T typed = gson.fromJson(jsonElement, clazz);
    
    return typed;
  }
  
  public <T> T getBody(Class<T> clazz){
    Gson gson = new Gson();
    JsonElement jsonElement = gson.toJsonTree(body);
    
    T typed = gson.fromJson(jsonElement, clazz);
    
    return typed;
  }
}
