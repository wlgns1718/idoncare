package d209.Idontcare.user.service;

import d209.Idontcare.common.exception.*;
import d209.Idontcare.user.dto.*;
import d209.Idontcare.user.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {

  Map<String,Object> login(Long userId, String password) throws Exception;

  Optional<User> findByUserId(Long userId);
  
  Optional<User> findByKakaoId(Long kakaoId);

  void joinUser(JoinUserReqDto joinUserDto)
      throws BadRequestException, DuplicatedException;
  
  Optional<User> findByPhoneNumber(String childPhoneNumber);

  public String findNameByUserId(Long userId) throws Exception;

}
