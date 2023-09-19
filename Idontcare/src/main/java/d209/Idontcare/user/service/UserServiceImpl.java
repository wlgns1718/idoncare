package d209.Idontcare.user.service;



import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.RegistFailException;
import d209.Idontcare.user.dto.AddressDto;
import d209.Idontcare.user.dto.JoinUserDto;
import d209.Idontcare.user.dto.UserDetailDto;
import d209.Idontcare.user.dto.UserDto;
import d209.Idontcare.user.entity.Address;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.entity.UserDetail;
import d209.Idontcare.user.repository.AddressRepository;
import d209.Idontcare.user.repository.UserDetailRepository;
import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final AddressRepository addressRepository;

    private static final int SALT_SIZE = 16;
    private String secretKey;
    private final String JWT_KEY = "idoncare";



    @Override
    public Optional<User> findByUserId(Long userId){
        return userRepository.findById(userId);
    }

    @Override
    public Long joinUser(JoinUserDto joinUserDto) throws RegistFailException {

        UserDto userDto = new UserDto();
        UserDetailDto userDetailDto = new UserDetailDto();
        AddressDto addressDto = new AddressDto();

        //user 생성
        userDto.setUserId(joinUserDto.getUserId());
        userDto.setNickName(joinUserDto.getNickName());
        userDto.setPhoneNumber(joinUserDto.getPhoneNumber());
        userDto.setRole(joinUserDto.getRole());
        userDto.setName(joinUserDto.getName());

        User user = User.toEntity(userDto);

        System.out.println(user);
        System.out.println("save 해보겠습니다");


        User saveUser = userRepository.save(user);
        System.out.println("save 완료");
        System.out.println(saveUser);


        //userDetail 생성
        userDetailDto.setUserId(joinUserDto.getUserId());
        userDetailDto.setBirth(joinUserDto.getBirth());
        userDetailDto.setEmail(joinUserDto.getEmail());

        UserDetail userDetail = new UserDetail();
        userDetail.toEntity(userDetailDto, saveUser);

        //userAddress 생성
        addressDto = joinUserDto.getAddressDto();

        Address address = new Address();
        address.toEntity(addressDto,saveUser);

        userDetailRepository.save(userDetail);
        addressRepository.save(address);

        return user.getUserId();
    }





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

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
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
