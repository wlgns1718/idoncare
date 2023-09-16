package d209.Idontcare.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import d209.Idontcare.common.dto.APIResultDto;

import java.util.Map;

public interface APIService {
  
  /* GET 요청 */
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path);
  
  <Body> APIResultDto<Map<String, String>, Body> get(String path, Class<Body> bodyType);
  
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Map<String, Object> headers);
  
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Object headers);
  
  <Body> APIResultDto<Map<String, String>, Body> get(String path, Map<String, Object> headers, Class<Body> bodyType);
  
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Map<String, Object> headers, Map<String, Object> queries);
  
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Object headers, Map<String, Object> queries);

  <Body> APIResultDto<Map<String, String>, Body> get(String path, Map<String, Object> headers, Map<String, Object> queries, Class<Body> bodyType);
  /* GET 요청 메서드 종료 */
  
  /* POST 요청 */
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path);

  <Body> APIResultDto<Map<String, String>, Body> post(String path, Class<Body> bodyType);

  APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Map<String, Object> headers);

  <Body> APIResultDto<Map<String, String>, Body> post(String path, Map<String, Object> headers, Class<Body> bodyType);
  
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Map<String, Object> headers, Map<String, Object> body);
  
  <Body> APIResultDto<Map<String, String>, Body> post(String path, Map<String, Object> headers, Map<String, Object> body, Class<Body> bodyType);
  /* POST 요청 메서드 종료 */
  

}
