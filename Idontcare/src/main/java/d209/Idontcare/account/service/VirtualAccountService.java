package d209.Idontcare.account.service;

import d209.Idontcare.account.dto.res.RealAccountRes;
import d209.Idontcare.account.dto.res.TransactionHistoryRes;
import d209.Idontcare.account.dto.req.VirtualToVirtualReq;
import d209.Idontcare.account.entity.RealAccount;
import d209.Idontcare.account.entity.Type;
import d209.Idontcare.account.entity.VirtualAccount;
import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.account.repository.VirtualAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class VirtualAccountService {

    public final VirtualAccountRepository virtualAccountRepository;
    public final TransactionHistoryService transactionHistoryService;
    public final EncryptService encryptService;

    //가상 계좌 잔액 찾기
    public Long userBalance(Long userId){
        return virtualAccountRepository.findBalance(userId);
    }
    
    //해당 사용자의 가상 계좌 찾기
    public Long userAccount(Long userId){
        return virtualAccountRepository.findUser(userId);
    }

    //가상 계좌끼리 거래
    public void virtualPayment(VirtualToVirtualReq request, Long startAccount) throws VirtualAccountException{
        LocalDateTime now = LocalDateTime.now();
        withdrawal(startAccount, request.getMoney(), request.getContent(), now);
        deposit(virtualAccountRepository.findUser(request.getUserId()), request.getMoney(), request.getContent(), now);
    }

    //가상 계좌에서 출금 + 거래내역에 추가
    public void withdrawal(Long startAccount, Long money, String content, LocalDateTime localDateTime) throws VirtualAccountException {
        VirtualAccount account = virtualAccountRepository.findById(startAccount).get();
        //가상 계좌에 잔액 부족
        if(account.getBalance() < money){
            throw new VirtualAccountException(402, "가상 계좌의 잔액 부족");
        }
        //가상 계좌에서 잔액 감소
        account.setBalance(account.getBalance() - money);
        //거래 내역에 추가
        transactionHistoryService.recordTransactionHistory(TransactionHistoryRes.builder()
                .userId(account.getUser().getUserId())
                .content(content)
                .localDateTime(localDateTime)
                .amount(money)
                .type(Type.WITHDRAWAL) //DEPOSITORY, WITHDRAWAL
                .balance(account.getBalance())
                .build());
    }

    //가상 계좌로 입금 + 거래내역에 추가
    public void deposit(Long destinateAccount, Long money, String content,LocalDateTime localDateTime){
        VirtualAccount account = virtualAccountRepository.findById(destinateAccount).get();
        //가상 계좌에서 잔액 감소
        account.setBalance(account.getBalance() + money);
        //거래 내역에 추가
        transactionHistoryService.recordTransactionHistory(TransactionHistoryRes.builder()
                .userId(account.getUser().getUserId())
                .content(content)
                .localDateTime(localDateTime)
                .amount(money)
                .type(Type.DEPOSITORY) //DEPOSITORY, WITHDRAWAL
                .balance(account.getBalance())
                .build());
    }

    //가상 계좌로 충전
    public void charge(Long userId, Long money){
        VirtualAccount virtualAccount = virtualAccountRepository.findByUserId(userId).orElseThrow(
                () -> new VirtualAccountException("가상 계좌를 찾을 수 없습니다."));
        deposit(virtualAccount.getVirtualAccountId(), money, "아이돈케어 가상 계좌에 " + money + "원 충전", LocalDateTime.now());
    }

    //가상 계좌에서 내 실 계좌로 출금
    public void paymentMe(Long userId, Long money){
        VirtualAccount virtualAccount = virtualAccountRepository.findByUserId(userId).orElseThrow(
                () -> new VirtualAccountException("가상 계좌를 찾을 수 없습니다."));
        withdrawal(virtualAccount.getVirtualAccountId(), money, "나의 충전 계좌로 " + money + "원 출금", LocalDateTime.now());
    }

    //가상 계좌에서 타 계좌로 출금
    public void paymentAnother(Long userId, Long money, String userName){
        VirtualAccount virtualAccount = virtualAccountRepository.findByUserId(userId).orElseThrow(
                () -> new VirtualAccountException("가상 계좌를 찾을 수 없습니다."));
        withdrawal(virtualAccount.getVirtualAccountId(), money, userName + "에게 " + money + "원 출금", LocalDateTime.now());
    }
}
