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
    public Optional<User> findByKakaoId(Long kakaoId) { return userRepository.findByKakaoId(kakaoId); }
    
    @Override
    public void joinUser(JoinUserReqDto req) throws RegistFailException {
        User user = userRepository.findById(req.getUserId()).orElseThrow(() -> new BadRequestException("잘못 된 요청입니다"));
        
        userRepository.findByPhoneNumber(req.getPhoneNumber()).ifPresent((u) -> {throw new DuplicatedException("사용중인 휴대폰번호입니다");});
        
        //회원가입은 사실상 유저정보 수정에 가깝다
        user.setPhoneNumber(req.getPhoneNumber());
        user.setName(req.getName());
        user.setRole(req.getRole());
        user.setNickName(req.getNickName());
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
