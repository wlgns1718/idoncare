package d209.Idontcare.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import d209.Idontcare.common.dto.APIResultDto;

import java.util.Map;

public interface APIService {
  
  /* GET 요청 */
  APIResultDto get(String path);
  APIResultDto get(String path, Map<String, Object> headers);
  APIResultDto get(String path, Object headers);
  APIResultDto get(String path, Object headers, Object queries);
  APIResultDto get(String path, Object headers, Map<String, Object> queries);
  APIResultDto get(String path, Map<String, Object> headers, Object queries);
  APIResultDto get(String path, Map<String, Object> headers, Map<String, Object> queries);
  /* GET 요청 메서드 종료 */
  
  /* POST 요청 */
  APIResultDto post(String path);
  APIResultDto post(String path, Map<String, Object> headers);
  APIResultDto post(String path, Object headers);
  APIResultDto post(String path, Object headers, Object body);
  APIResultDto post(String path, Object headers, Map<String, Object> body);
  APIResultDto post(String path, Map<String, Object> headers, Object body);
  APIResultDto post(String path, Map<String, Object> headers, Map<String, Object> body);
  /* POST 요청 메서드 종료 */
  
  /* PUT 요청 */
  APIResultDto put(String path);
  APIResultDto put(String path, Map<String, Object> headers);
  APIResultDto put(String path, Object headers);
  APIResultDto put(String path, Object headers, Object body);
  APIResultDto put(String path, Object headers, Map<String, Object> body);
  APIResultDto put(String path, Map<String, Object> headers, Object body);
  APIResultDto put(String path, Map<String, Object> headers, Map<String, Object> body);
  /* PUT 요청 메서드 종료 */
  
  
  /* PUT 요청 */
  APIResultDto delete(String path);
  APIResultDto delete(String path, Map<String, Object> headers);
  APIResultDto delete(String path, Object headers);
  APIResultDto delete(String path, Object headers, Object body);
  APIResultDto delete(String path, Object headers, Map<String, Object> body);
  APIResultDto delete(String path, Map<String, Object> headers, Object body);
  APIResultDto delete(String path, Map<String, Object> headers, Map<String, Object> body);
  /* PUT 요청 메서드 종료 */
}
