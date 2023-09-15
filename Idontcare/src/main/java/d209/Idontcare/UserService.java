package d209.Idontcare;

import d209.Idontcare.common.exception.AuthenticationException;
import d209.Idontcare.common.exception.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository userRepository;
  
  public User findByUserId(Long userId) throws NoSuchUserException {
    return userRepository.findById(userId).orElseThrow(NoSuchUserException::new);
  }
  
  public User findByPhoneNumber(String phoneNumber) throws NoSuchUserException{
    return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(NoSuchUserException::new);
  }
}
