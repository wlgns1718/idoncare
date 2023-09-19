package d209.Idontcare;

import d209.Idontcare.common.APIBuilder;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.dto.TestBody;
import d209.Idontcare.dto.TestHeader;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import d209.Idontcare.common.service.APIService;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class RestTests {
  
  private String URL = "http://localhost:3000";

  @Autowired
  private APIService apiService;
  
  @Test
  @DisplayName("GET 테스트")
  void getTest() {
    APIResultDto result = APIBuilder.build()
        .url("http://localhost:3000")
        .method(HttpMethod.POST)
        .mediaType(MediaType.APPLICATION_FORM_URLENCODED)
        .header(Map.of("header1", "headerValue1", "header2", 2))
        .query(Map.of("query1", 1, "query2", "q_value2"))
        .body(Map.of("name", "이우철", "age", 25))
        .execute();
    
    System.out.println(result.getStatus());
    System.out.println(result.getHeader());
    System.out.println(result.getBody());
    ResponseBody body = result.getBody(ResponseBody.class);
    System.out.println(body);
  }
}

class ResponseBody{
  String result;
  
  public String getResult() {
    return result;
  }
  
  public void setResult(String result) {
    this.result = result;
  }
  
  @Override
  public String toString() {
    return "ResponseBody{" +
        "result='" + result + '\'' +
        '}';
  }
}