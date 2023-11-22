package d209.Idontcare.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import d209.Idontcare.common.dto.APIResultDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class APIBuilder{
  private ObjectMapper mapper;
  
  private HttpMethod method;
  private String url;
  
  private MediaType mediaType;
  
  private Class returnType;
  
  private Map<String, Object> header;
  private Map<String, Object> query;
  private Map<String, Object> body;
  
  private APIBuilder(){}
  
  public static APIBuilder build(){
    APIBuilder builder = new APIBuilder();
    
    builder.mapper = new ObjectMapper();
    
    builder.method = null;
    builder.url = null;
    builder.mediaType = null;
    
    builder.header = new HashMap<>();
    builder.query = new HashMap<>();
    builder.body = new HashMap<>();
    
    builder.returnType = new HashMap<String, Object>().getClass();
    
    return builder;
  }
  
  public APIBuilder method(HttpMethod method){
    this.method = method;
    return this;
  }
  public APIBuilder url(String url){
    this.url = url;
    return this;
  }
  
  public APIBuilder mediaType(MediaType mediaType){
    this.mediaType = mediaType;
    return this;
  }
  
  public APIBuilder header(Map<String, Object> header){
    this.header = header;
    return this;
  }
  public APIBuilder header(Object header){
    this.header = mapper.convertValue(header, new TypeReference<Map<String, Object>>() {});
    return this;
  }
  
  public APIBuilder query(Map<String, Object> query){
    this.query = query;
    return this;
  }
  public APIBuilder query(Object query){
    this.query = mapper.convertValue(query, new TypeReference<Map<String, Object>>() {});
    return this;
  }
  
  public APIBuilder body(Map<String, Object> body){
    this.body = body;
    return this;
  }
  public APIBuilder body(Object body){
    this.body = mapper.convertValue(body, new TypeReference<Map<String, Object>>() {});
    return this;
  }
  
  public APIBuilder returnType(Class returnType){
    this.returnType = returnType;
    return this;
  }
  
  public APIResultDto execute(){
    if(method == null) throw new RuntimeException("method 설정이 필요합니다");
    if(url == null) throw new RuntimeException("url 설정이 필요합니다");
    
    /* 파라미터를 포함한 URL만들기 */
    UriComponentsBuilder uriBuilder = UriComponentsBuilder
        .fromUriString(url);
    
    for(Map.Entry<String, Object> q: this.query.entrySet())uriBuilder.queryParam(q.getKey(), q.getValue());
    
    URI uri = uriBuilder.encode().build().toUri();
    
    /* 헤더 정의 */
    HttpHeaders requestHeader = new HttpHeaders();
    if(mediaType == null && !body.isEmpty()){
      requestHeader.setContentType(MediaType.APPLICATION_JSON);
    }
    requestHeader.setContentType(mediaType);
    for(Map.Entry<String, Object> h: header.entrySet()) requestHeader.set(h.getKey(), h.getValue().toString());
    
    /* 요청 정의 */
    HttpEntity entity = null;
    if(mediaType == MediaType.APPLICATION_FORM_URLENCODED){
      // 타입이 url_encoded이면
      MultiValueMap<String, Object> multiValueBody = new LinkedMultiValueMap<>();
      multiValueBody.setAll(body);
      
      entity = new HttpEntity(multiValueBody, requestHeader);
    }
    else{
      // 다른 타입이면
      entity = new HttpEntity(body, requestHeader);
    }
    
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity response = restTemplate.exchange(
        uri,
        method,
        entity,
        returnType
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
}