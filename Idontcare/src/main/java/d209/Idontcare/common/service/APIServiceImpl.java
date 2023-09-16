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
import java.util.Map;

@Service
public class APIServiceImpl implements APIService {
  
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
  
  @Override
  public APIResultDto<Map<String, String>, Map<String, Object>> get(String path) {
    return get(path, (Map<String, Object>) null, (Map<String, Object>) null);
  }
  
  @Override
  public APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Map<String, Object> headers) {
    return get(path, headers, (Map<String, Object>) null);
  }
  
  @Override
  public APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Object headers) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> map = mapper.convertValue(headers, Map.class);
    
    return get(path, map);
  }
  
  @Override
  public APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Map<String, Object> headers, Map<String, Object> queries){
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
    
    System.out.println(response.getBody());
    
    APIResultDto<Map<String, String>, Map<String, Object>> apiResult = new APIResultDto<>();
    
    // 결과 status 저장
    apiResult.setStatus(response.getStatusCode());
    
    // 결과 Header 저장
    apiResult.setHeader(response.getHeaders().toSingleValueMap());
    
    // 결과 body 저장
    apiResult.setBody(response.getBody());
    
    return apiResult;
  }
  
  @Override
  public APIResultDto<Map<String, String>, Map<String, Object>> get(String path, Object headers, Map<String, Object> queries) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> map = mapper.convertValue(headers, Map.class);
    
    return get(path, map, queries);
  }
  
  @Override
  public APIResultDto<Map<String, String>, Map<String, Object>> post(String path) {
    return post(path, (Map<String, Object>) null, (Map<String, Object>)null);
  }
  
  @Override
  public APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Map<String, Object> headers) {
    return post(path, headers, (Map<String, Object>)null);
  }
  
  
  @Override
  public APIResultDto<Map<String, String>, Map<String, Object>> post(String path, Map<String, Object> headers, Map<String, Object> body){
    //요청 헤더 정의
    HttpHeaders requestHeader = new HttpHeaders();
    if(headers != null){
      for(Map.Entry<String, Object> header: headers.entrySet()) requestHeader.set(header.getKey(), header.getValue().toString());
    }
    
    HttpEntity<Map<String, Object>> entity = new HttpEntity(body, requestHeader);
    
    //API 요청하기
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Map<String, Object>> response = restTemplate.exchange(path,
        HttpMethod.POST,
        entity,
        new ParameterizedTypeReference<Map<String, Object>>(){}
    );
    
    APIResultDto<Map<String, String>, Map<String, Object>> apiResult = new APIResultDto<>();
    
    // 결과 status 저장
    apiResult.setStatus(response.getStatusCode());
    
    // 결과 Header 저장
    apiResult.setHeader(response.getHeaders().toSingleValueMap());
    
    // 결과 body 저장
    apiResult.setBody(response.getBody());
    
    return apiResult;
  }
  
}