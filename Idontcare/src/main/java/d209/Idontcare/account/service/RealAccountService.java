package d209.Idontcare.account.service;

import d209.Idontcare.account.dto.res.RealAccountRes;
import d209.Idontcare.account.entity.RealAccount;
import d209.Idontcare.account.exception.VirtualAccountException;
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
    public RealAccountRes findRealAccount(Long userId) {
        return realAccountRepository.findAccount(userId)
                .map(account -> {
                    String decrypAccount = encryptService.decryp(account.getRealAccountId());
                    String decrypPinNumber = encryptService.decryp(account.getPinNumber());
                    return RealAccountRes.RealAccountToDto(account, decrypAccount, decrypPinNumber);
                })
                .orElseThrow(() -> new VirtualAccountException(402, "충전 계좌가 등록 되지 않았습니다."));
    }
}
