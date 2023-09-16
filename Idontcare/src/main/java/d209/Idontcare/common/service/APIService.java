package d209.Idontcare.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import d209.Idontcare.common.dto.APIResultDto;

import java.util.Map;

public interface APIService {
  
  /* GET 요청 */
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path);
  
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Map<String, Object> headers);
  
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Object headers);
  
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Object headers, Object queries);
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Object headers, Map<String, Object> queries);
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Map<String, Object> headers, Object queries);
  APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Map<String, Object> headers, Map<String, Object> queries);
  /* GET 요청 메서드 종료 */
  
  /* POST 요청 */
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path);
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Map<String, Object> headers);
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Object headers);
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Object headers, Object body);
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Object headers, Map<String, Object> body);
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Map<String, Object> headers, Object body);
  APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Map<String, Object> headers, Map<String, Object> body);
  /* POST 요청 메서드 종료 */
  

}
