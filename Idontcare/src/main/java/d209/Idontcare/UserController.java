package d209.Idontcare;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class UserController {
  
  private final UserRepository userRepository;
  
  @PostConstruct
  public void insertInitData(){
    User user1 = new User().setUUID();
    User user2 = new User().setUUID();
    User user3 = new User().setUUID();
    
    user1.setName("엄마지훈");
    user1.setType(User.Type.PARENT);
    user1.setPhoneNumber("01012345678");
    
    user2.setName("아들지훈");
    user2.setType(User.Type.CHILD);
    user2.setPhoneNumber("01098765432");
    
    user3.setName("딸지훈");
    user3.setType(User.Type.CHILD);
    user3.setPhoneNumber("0101472583");
    
    List<User> users = new LinkedList<>();
    users.add(user1);
    users.add(user2);
    users.add(user3);
    
    userRepository.saveAll(users);
  }
}
