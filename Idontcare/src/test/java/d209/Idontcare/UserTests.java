package d209.Idontcare;

import d209.Idontcare.user.dto.GetUserInfoDto;
import d209.Idontcare.user.service.OauthService;
import d209.Idontcare.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private OauthService oauthService;

  @Test
  @DisplayName("카카오 로그인 해보기")
  void loginTest(){
  }
}
