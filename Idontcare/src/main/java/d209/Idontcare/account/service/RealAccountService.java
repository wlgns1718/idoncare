package d209.Idontcare.account.service;

import d209.Idontcare.account.repository.RealAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class RealAccountService {

    public final RealAccountRepository realAccountRepository;
    public final EncryptService encryptService;

    //실계좌 등록 했는지 확인하기
    public void findRealAccount(Long userId){
        String encryptionAccount = realAccountRepository.findAccount(userId);
        String decryp = encryptService.decryp(encryptionAccount);
    }
}
