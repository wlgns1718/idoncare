package d209.Idontcare.user.service;

import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.RegistFailException;
import d209.Idontcare.user.dto.AddressDto;
import d209.Idontcare.user.dto.JoinUserDto;
import d209.Idontcare.user.dto.UserDetailDto;
import d209.Idontcare.user.dto.UserDto;
import d209.Idontcare.user.entity.User;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {

    public Map<String,Object> login(Long userId, String password) throws Exception;

    public Optional<User> findByUserId(Long userId);

    public Long joinUser(JoinUserDto joinUserDto)throws RegistFailException;

}
