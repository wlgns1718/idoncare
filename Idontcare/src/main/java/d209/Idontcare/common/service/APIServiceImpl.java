package d209.Idontcare.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import d209.Idontcare.common.dto.APIResultDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class APIServiceImpl implements APIService {
  
  /* 공통 로직 */
  private URI uriBuild(String path, Map<String, Object> queries){
    UriComponentsBuilder builder = UriComponentsBuilder
        .fromUriString(path);
    if(queries != null){
      for(Map.Entry<String, Object> query: queries.entrySet()){
        builder.queryParam(query.getKey(), query.getValue());
      }
    }
    
    return builder.encode().build().toUri();
  }
  private Map<String, Object> toMap(Object obj){
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> map = mapper.convertValue(obj, Map.class);
    return map;
  }
  private APIResultDto request(HttpMethod method, String path, Map<String, Object> headers, Map<String, Object> body){
    //요청 헤더 정의
    HttpHeaders requestHeader = new HttpHeaders();
    if(headers != null){
      for(Map.Entry<String, Object> header: headers.entrySet()) requestHeader.set(header.getKey(), header.getValue().toString());
    }
    
    HttpEntity<Map<String, Object>> entity = new HttpEntity(body, requestHeader);
    
    //API 요청하기
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Map<String, Object>> response = restTemplate.exchange(path,
        method,
        entity,
        new ParameterizedTypeReference<Map<String, Object>>(){}
    );
    
    APIResultDto apiResult = new APIResultDto();
    
    // 결과 status 저장
    apiResult.setStatus(response.getStatusCode());
    
    // 결과 Header 저장
    apiResult.setHeader(response.getHeaders().toSingleValueMap());
    
    // 결과 body 저장
    apiResult.setBody(response.getBody());
    
    return apiResult;
  }
  
  
  /* GET 요청 */
  @Override
  public APIResultDto get(String path) {
    return get(path, (Map<String, Object>) null, (Map<String, Object>) null);
  }
  @Override
  public APIResultDto get(String path, Map<String, Object> headers) {
    return get(path, headers, (Map<String, Object>) null);
  }
  @Override
  public APIResultDto get(String path, Object headers) {
    Map<String, Object> map = toMap(headers);
    return get(path, map);
  }
  @Override
  public APIResultDto get(String path, Object headers, Object queries) {
    Map<String, Object> headersMap = toMap(headers);
    Map<String, Object> queriesMap = toMap(queries);
    
    return get(path, headersMap, queriesMap);
  }
  @Override
  public APIResultDto get(String path, Map<String, Object> headers, Map<String, Object> queries){
    //path에 파라미터 같이 붙이기
    URI uri = uriBuild(path, queries);
   
    //요청 헤더 정의
    HttpHeaders requestHeader = new HttpHeaders();
    if(headers != null){
      for(Map.Entry<String, Object> header: headers.entrySet()) requestHeader.set(header.getKey(), header.getValue().toString());
    }
    
    //API 요청하기
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Map<String, Object>> response = restTemplate.exchange(uri,
                                                                          HttpMethod.GET,
                                                                          new HttpEntity(requestHeader),
                                                                          new ParameterizedTypeReference<Map<String, Object>>(){}
                                                                        );
    
    APIResultDto apiResult = new APIResultDto();
    
    // 결과 status 저장
    apiResult.setStatus(response.getStatusCode());
    
    // 결과 Header 저장
    apiResult.setHeader(response.getHeaders().toSingleValueMap());
    
    // 결과 body 저장
    apiResult.setBody(response.getBody());
    
    return apiResult;
  }
  @Override
  public APIResultDto get(String path, Object headers, Map<String, Object> queries) {
    Map<String, Object> map = toMap(headers);
    return get(path, map, queries);
  }
  @Override
  public APIResultDto get(String path, Map<String, Object> headers, Object queries) {
    Map<String, Object> map = toMap(queries);
    
    return get(path, headers, map);
  }
  
  /* POST요청 */
  @Override
  public APIResultDto post(String path) {
    return post(path, (Map<String, Object>) null, (Map<String, Object>)null);
  }
  @Override
  public APIResultDto post(String path, Map<String, Object> headers) {
    return post(path, headers, (Map<String, Object>)null);
  }
  @Override
  public APIResultDto post(String path, Object headers) {
    Map<String, Object> map = toMap(headers);
    return post(path, map, null);
  }
  public APIResultDto post(String path, Object headers, Object body){
    Map<String, Object> headersMap = toMap(headers);
    Map<String, Object> bodyMap = toMap(body);
    
    return post(path, headersMap, bodyMap);
  }
  public APIResultDto post(String path, Object headers, Map<String, Object> body){
    Map<String, Object> map = toMap(headers);
    return post(path, map, body);
  }
  public APIResultDto post(String path, Map<String, Object> headers, Object body){
    Map<String, Object> map = toMap(body);
    
    return post(path, headers, map);
  }
  @Override
  public APIResultDto post(String path, Map<String, Object> headers, Map<String, Object> body){
    return request(HttpMethod.POST, path, headers, body);
  }
  
  /* PUT요청 */
  @Override
  public APIResultDto put(String path) {
    return put(path, (Map<String, Object>) null, (Map<String, Object>)null);
  }
  @Override
  public APIResultDto put(String path, Map<String, Object> headers) {
    return put(path, headers, (Map<String, Object>)null);
  }
  @Override
  public APIResultDto put(String path, Object headers) {
    Map<String, Object> map = toMap(headers);
    return put(path, map, null);
  }
  public APIResultDto put(String path, Object headers, Object body){
    Map<String, Object> headersMap = toMap(headers);
    Map<String, Object> bodyMap = toMap(body);
    
    return put(path, headersMap, bodyMap);
  }
  public APIResultDto put(String path, Object headers, Map<String, Object> body){
    Map<String, Object> map = toMap(headers);
    return put(path, map, body);
  }
  public APIResultDto put(String path, Map<String, Object> headers, Object body){
    Map<String, Object> map = toMap(body);
    
    return put(path, headers, map);
  }
  @Override
  public APIResultDto put(String path, Map<String, Object> headers, Map<String, Object> body){
    return request(HttpMethod.PUT, path, headers, body);
  }
  
  
  /* DELETE요청 */
  @Override
  public APIResultDto delete(String path) {
    return delete(path, (Map<String, Object>) null, (Map<String, Object>)null);
  }
  @Override
  public APIResultDto delete(String path, Map<String, Object> headers) {
    return delete(path, headers, (Map<String, Object>)null);
  }
  @Override
  public APIResultDto delete(String path, Object headers) {
    Map<String, Object> map = toMap(headers);
    return delete(path, map, null);
  }
  public APIResultDto delete(String path, Object headers, Object body){
    Map<String, Object> headersMap = toMap(headers);
    Map<String, Object> bodyMap = toMap(body);
    
    return delete(path, headersMap, bodyMap);
  }
  public APIResultDto delete(String path, Object headers, Map<String, Object> body){
    Map<String, Object> map = toMap(headers);
    return delete(path, map, body);
  }
  public APIResultDto delete(String path, Map<String, Object> headers, Object body){
    Map<String, Object> map = toMap(body);
    
    return delete(path, headers, map);
  }
  @Override
  public APIResultDto delete(String path, Map<String, Object> headers, Map<String, Object> body){
    return request(HttpMethod.DELETE, path, headers, body);
  }
  
}
