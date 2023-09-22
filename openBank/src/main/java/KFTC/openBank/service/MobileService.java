package KFTC.openBank.service;

import KFTC.openBank.domain.Mobile;
import KFTC.openBank.dto.AuthRequestDto;
import KFTC.openBank.exception.MobileException;
import KFTC.openBank.repository.MobileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MobileService {

    public final MobileRepository mobileRepository;
    public boolean findMobile(AuthRequestDto authRequestDto) throws MobileException{
        Optional<Mobile> temp = mobileRepository.findById(authRequestDto.getPhoneNumber());
        if(temp.isEmpty()){
            throw new MobileException.NotCorrect("해당하는 휴대폰 번호는 없습니다.");
        }
        Mobile mobile = temp.get();
        if(!mobile.getName().equals(authRequestDto.getName()) || !mobile.getBirth().equals(authRequestDto.getBirth()) || !(mobile.getMobileSort() == authRequestDto.getMobileSort())){
            throw new MobileException.NotCorrect("정보가 일치하지 않습니다.");
        }
        return true;
    }
}
