package d209.Idontcare.user.service;


import d209.Idontcare.User;
import d209.Idontcare.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private static final int SALT_SIZE = 16;
    private String secretKey;
    private final String JWT_KEY = "idoncare";

    @Override
    public Map<String, Object> login(Long userId, String password) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user == null){
            log.info("등록되지 않은 사용자 입니다.");
            throw new Exception();
        }
        Map<String,Object> res = new HashMap<>();
        return res;
    }

    // 비밀번호 해싱
    private String hashing(String password, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");// SHA-256 해시함수를 이용
        for (int i = 0; i < 10000; i++) {// salt를 합쳐 새로운 해시비밀번호를 생성해 디코딩를 방지
            String temp = password + salt;// 패스워드와 Salt를 합쳐 새로운 문자열 생성
            md.update(temp.getBytes());// temp의 문자열을 해싱하여 md에 저장
            password = byteToString(md.digest());// md객체의 다이제스트를 얻어 password를 갱신
        }
        return password;
    }

    private String byteToString(byte[] temp) {// 바이트 값을 16진수로 변경
        StringBuilder sb = new StringBuilder();
        for (byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }
}
