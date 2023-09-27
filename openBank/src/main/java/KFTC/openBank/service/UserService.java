package KFTC.openBank.service;

import KFTC.openBank.domain.Role;
import KFTC.openBank.domain.User;
import KFTC.openBank.dto.AuthRequestDto;
import KFTC.openBank.exception.UserException;
import KFTC.openBank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void createUser(AuthRequestDto authRequestDto) throws Exception {
        userRepository.findUser(authRequestDto.getName(), authRequestDto.getPhoneNumber()).ifPresent(
                user -> {
                    throw new UserException.AlreadySaveException("이미 등록된 회원입니다.");
                }
        );
        userRepository.save(new User(authRequestDto.getName(), authRequestDto.getPhoneNumber(), Role.INDIVIDUAL));
    }
}
