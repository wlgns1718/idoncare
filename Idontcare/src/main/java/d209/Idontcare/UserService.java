package d209.Idontcare;

import d209.Idontcare.common.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository userRepository;
  
  public User findByUserId(Long userId){
    return userRepository.findById(userId).orElseThrow(AuthenticationException::new);
  }
}
