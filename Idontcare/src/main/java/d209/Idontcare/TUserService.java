package d209.Idontcare;

import d209.Idontcare.common.exception.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TUserService {
  
  private final TUserRepository TUserRepository;
  
  public TUser findByUserId(Long userId) throws NoSuchUserException {
    return TUserRepository.findById(userId).orElseThrow(NoSuchUserException::new);
  }
  
  public TUser findByPhoneNumber(String phoneNumber) throws NoSuchUserException{
    return TUserRepository.findByPhoneNumber(phoneNumber).orElseThrow(NoSuchUserException::new);
  }
}
