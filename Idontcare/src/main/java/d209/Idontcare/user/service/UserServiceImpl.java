package d209.Idontcare.user.service;



import d209.Idontcare.common.exception.*;
import d209.Idontcare.user.dto.JoinUserReqDto;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.entity.UserDetail;
import d209.Idontcare.user.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUserId(Long userId){
        return userRepository.findById(userId);
    }

    @Override
    public void joinUser(JoinUserReqDto req) throws RegistFailException {
        userRepository.findById(req.getUserId()).ifPresent((u) -> {throw new BadRequestException("가입된 회원입니다");});
        userRepository.findByPhoneNumber(req.getPhoneNumber()).ifPresent((u) -> {throw new DuplicatedException("사용중인 휴대폰번호입니다");});
        
        User user = User.builder()
            .userId(req.getUserId())
            .phoneNumber(req.getPhoneNumber())
            .name(req.getName())
            .role(req.getRole())
            .nickName(req.getNickName())
            .build();

        User savedUser = userRepository.save(user);
        
        UserDetail userDetail = UserDetail.builder()
            .userDetailId(req.getUserId())
            .user(savedUser)
            .email(req.getEmail())
            .build();

        userDetailRepository.save(userDetail);
    }
    
    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
    
    
    @Override
    public Map<String, Object> login(Long userId, String password) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new AuthenticationException();
        }
        Map<String,Object> res = new HashMap<>();
        return res;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }


}
