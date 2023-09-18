package d209.Idontcare;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TUserController {
  
  private final TUserRepository TUserRepository;
  
  @PostConstruct
  public void insertInitData(){
    TUser TUser1 = new TUser().setUUID();
    TUser TUser2 = new TUser().setUUID();
    TUser TUser3 = new TUser().setUUID();
    
    TUser1.setName("엄마지훈");
    TUser1.setType(TUser.Type.PARENT);
    TUser1.setPhoneNumber("01012345678");
    
    TUser2.setName("아들지훈");
    TUser2.setType(TUser.Type.CHILD);
    TUser2.setPhoneNumber("01098765432");
    
    TUser3.setName("딸지훈");
    TUser3.setType(TUser.Type.CHILD);
    TUser3.setPhoneNumber("0101472583");
    
    List<TUser> TUsers = new LinkedList<>();
    TUsers.add(TUser1);
    TUsers.add(TUser2);
    TUsers.add(TUser3);
    
    TUserRepository.saveAll(TUsers);
  }
}
