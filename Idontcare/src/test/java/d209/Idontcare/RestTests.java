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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class RestTests {

  private String URL = "http://localhost:8080";

  @Autowired
  private APIService apiService;
  
  @Test
  @DisplayName("GET 테스트")
  void getTest() {
    APIResultDto result = APIBuilder.build()
        .url("https://port-0-openbankapi-iciy2almk8xusg.sel5.cloudtype.app/openbanking/oauth/2.0/token")
        .method(HttpMethod.POST)
        .body(Map.of(  "phoneNumber", "01012345678",
                  "birth", "1990101",
                  "mobileSort", "SK",
                  "name", "김엄마"))
        .execute();
    
    System.out.println(result.getStatus());
    System.out.println(result.getHeader());
    System.out.println(result.getBody());
    
    result = APIBuilder.build()
        .url("http://localhost:3000")
        .method(HttpMethod.PUT)
        .body(Map.of("age", 1, "name", "hello"))
        .execute();
    
    System.out.println(result.getStatus());
    System.out.println(result.getHeader());
    System.out.println(result.getBody());
  }
}
