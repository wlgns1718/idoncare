package d209.Idontcare.account.service;

import d209.Idontcare.account.dto.res.RealAccountRes;
import d209.Idontcare.account.entity.RealAccount;
import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.account.repository.RealAccountRepository;
import d209.Idontcare.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
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

    //충전 계좌 등록하기
    public void saveRealAccount(String accountNum, User user, String pinNumber, String bankName, String bankCode){
        realAccountRepository.save(new RealAccount(encryptService.encrypt(accountNum), user, encryptService.encrypt(pinNumber), bankName, bankCode));
    }

    //충전 계좌 삭제하기
    public void deleteRealAccount(User user) throws AccountException {
        RealAccount realAccount = realAccountRepository.findAccount(user.getUserId()).orElseThrow(() -> new AccountException("등록된 충전 계좌가 없습니다."));
        realAccountRepository.delete(realAccount);
    }
}
