package d209.Idontcare;

import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.dto.TestBody;
import d209.Idontcare.dto.TestHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import d209.Idontcare.common.service.APIService;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class RestTests {
  
  private String URL = "http://localhost:3000";

  @Autowired
  private APIService apiService;
  
  @Test
  @DisplayName("GET 테스트")
  void getTest(){
    Map<String, Object> headers = new HashMap<>();
    headers.put("header_value1", 1);
    headers.put("header_value2", 2);
    
    Map<String, Object> queries = new HashMap<>();
    queries.put("param1", "ㅁㄴㅇㄹ");
    queries.put("param2", 12345);
    
    APIResultDto<Map<String, String>, Map<String, Object>> result = null;
    result = apiService.get(URL);
    System.out.println(result.getBody());
    result = apiService.get(URL, headers);
    System.out.println(result.getBody());
    result = apiService.get(URL, headers, queries);
    System.out.println(result.getBody());
    result = apiService.get(URL, null, queries);
    System.out.println(result.getBody());
    
    System.out.println("TYPE START");
    
    APIResultDto<Map<String, String>, TestBody> result2 = null;
    result2 = apiService.get(URL, headers, queries, TestBody.class);
    System.out.println(result2.getBody());
    result2 = apiService.get(URL, headers, TestBody.class);
    System.out.println(result2.getBody());
    result2 = apiService.get(URL, headers, queries, TestBody.class);
    System.out.println(result2.getBody());
    result2 = apiService.get(URL, null, queries, TestBody.class);
    System.out.println(result2.getBody());
    
    System.out.println("HEADER START");
    TestHeader headerDto = new TestHeader("hello", 1);
    result = apiService.get(URL, headerDto);
    System.out.println(result.getBody());
    result = apiService.get(URL, headerDto, queries);
    System.out.println(result.getBody());
  }
  
  @Test
  @DisplayName("POST 테스트")
  void postTest(){
    Map<String, Object> headers = new HashMap<>();
    headers.put("header_value1", 1);
    headers.put("header_value2", 2);
    
    Map<String, Object> body = new HashMap<>();
    body.put("name", "이우철");
    body.put("age", 22);
    
    APIResultDto<Map<String, String>, Map<String, Object>> result = null;
    result = apiService.post(URL);
    System.out.println(result.getBody());
    result = apiService.post(URL, headers);
    System.out.println(result.getBody());
    result = apiService.post(URL, headers, body);
    System.out.println(result.getBody());
    result = apiService.post(URL, null, body);
    System.out.println(result.getBody());
    
    System.out.println("Type Start");
    
    APIResultDto<Map<String, String>, TestBody> result2 = null;
    result2 = apiService.post(URL, TestBody.class);
    System.out.println(result2.getBody());
    result2 = apiService.post(URL, headers, TestBody.class);
    System.out.println(result2.getBody());
    result2 = apiService.post(URL, headers, body, TestBody.class);
    System.out.println(result2.getBody());
    result2 = apiService.post(URL, null, body, TestBody.class);
    System.out.println(result2.getBody());
    
  }
}
