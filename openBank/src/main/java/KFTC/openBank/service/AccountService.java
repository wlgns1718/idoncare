package KFTC.openBank.service;


import KFTC.openBank.domain.BankAccount;
import KFTC.openBank.domain.TransactionHistory;
import KFTC.openBank.domain.Type;
import KFTC.openBank.dto.*;
import KFTC.openBank.exception.AccountException;
import KFTC.openBank.exception.BackAccountException;
import KFTC.openBank.repository.AccountRepository;
import KFTC.openBank.repository.BankAccountRepository;
import KFTC.openBank.repository.BankRepository;
import KFTC.openBank.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    //플랫폼에 등록된 계좌에서 잔액 조회
    @Transactional(readOnly = true)
    public BalanceResponseDto findBalance(String pinNumber) throws AccountException {
        Tuple result = accountRepository.findBankAndAccountNumberById(pinNumber);
        if(result==null){
           throw new AccountException.FintechNumNotFoundException("요청 하신 핀테크 이용 번호는 등록되어 있지 않습니다.");
        }
        //은행 코드 및 계좌 번호
        String bankId = (String) result.get(0);
        String accountNumber = (String) result.get(1);
        //은행 이름
        String bankName = bankRepository.findNameById(bankId);
        Long money = bankAccountRepository.findMoneyByIdAndBankId(accountNumber, bankId);
        return new BalanceResponseDto(bankName,money);
    }
    
    //계좌 실명 조회
    @Transactional(readOnly = true)
    public InquiryResponseDto findRealName(InquiryRequestDto inquiryRequestDto) throws AccountException {
        String accountName = bankAccountRepository.findNameById(inquiryRequestDto.getAccountNum(), inquiryRequestDto.getBankCodeStd(), inquiryRequestDto.getAccountHolderInfo());
        if(accountName == null){
            throw new AccountException.AccoutNotFoundException("요청 하신 정보와 일치하는 계좌는 없습니다.");
        }
        return new InquiryResponseDto(accountName);
    }

    //입금 이체

    //출금 이체
    @Transactional(rollbackFor = {AccountException.class, BackAccountException.class})
    public WithdrawReponseDto withdrawLogic(WithdrawRequestDto withdrawRequestDto) throws AccountException, BackAccountException {
        Tuple result = accountRepository.findBankAndAccountNumberById(withdrawRequestDto.getFintechUseNum());
        if(result==null){
            throw new AccountException.FintechNumNotFoundException("요청 하신 핀테크 이용 번호는 등록되어 있지 않습니다.");
        }
        //출금자의 은행 코드 및 계좌 번호
        String bankId = (String) result.get(0);
        String accountNumber = (String) result.get(1);
        String bankName = bankRepository.findNameById(bankId);
        //입금 하고자 하는 계좌의 소유자
        String depositName = bankAccountRepository.findNameByIdAndBankId(withdrawRequestDto.getCntrAccountNum(), withdrawRequestDto.getCntrAccountBankCodeStd());
        if(depositName.equals(null)){
            throw new AccountException.AccoutNotFoundException("입금을 원하는 계좌는 없는 계좌입니다.");
        }
        //출금 계좌와 은행 id로 잔액 조회.
        Long money = bankAccountRepository.findMoneyByIdAndBankId(accountNumber, bankId);
        if(withdrawRequestDto.getTran_amt() > money) {
            throw new AccountException.AccoutInsufficientException("출금 계좌의 잔액이 부족합니다.");
        }
        if(withdrawRequestDto.getTran_amt() <= 0) {
            throw new AccountException.AccoutInsufficientException("최소 1원 이상 이체가 가능합니다.");
        }
        PaymentDto payment = new PaymentDto(withdrawRequestDto.getReqClientName(), bankId, bankName, withdrawRequestDto.getWdPrintContent(), accountNumber, depositName, withdrawRequestDto.getCntrAccountBankCodeStd(), bankRepository.findNameById(withdrawRequestDto.getCntrAccountBankCodeStd()), withdrawRequestDto.getDpsPrintContent(), withdrawRequestDto.getCntrAccountNum(), withdrawRequestDto.getTran_amt());
        withdraw(payment);
        deposit(payment);
        return new WithdrawReponseDto("A0000");
    }
//    public TransactionHistory(BankAccount bankAccount, String content, LocalDateTime localDateTime, Long amount, Type type, Long balance) {

    //출금
    public void withdraw(PaymentDto paymentDto) throws BackAccountException{
        BankAccount account = bankAccountRepository.findByNameAndBankIdAndId(paymentDto.getWithdrawer(), paymentDto.getWithdrawerBankId(), paymentDto.getWithdrawerAccountNum());
        if(account == null){
            throw new BackAccountException.WithdrawException("출금하려는 계좌의 정보가 없습니다.");
        }
        try{
            BankAccount bankAccount = bankAccountRepository.findById(paymentDto.getWithdrawerAccountNum()).get();
            Long money = account.withdraw(paymentDto.getMoney());
            TransactionHistory transactionHistory = new TransactionHistory(bankAccount, paymentDto.getWithdrawerContent(), LocalDateTime.now(), paymentDto.getMoney(), Type.WITHDRAWAL, money);
            transactionHistoryRepository.save(transactionHistory);
        }catch (Exception e){
            throw new BackAccountException.WithdrawException("출금 중 문제가 발생했습니다!");
        }
    }

    //입금
    public void deposit(PaymentDto paymentDto) throws BackAccountException {
        BankAccount account = bankAccountRepository.findByNameAndBankIdAndId(paymentDto.getDepositor(), paymentDto.getDepositorBankId(), paymentDto.getDepositorAccountNum());
        if(account == null){
            throw new BackAccountException.WithdrawException("입금하려는 계좌의 정보가 없습니다.");
        }
        try{
            BankAccount bankAccount = bankAccountRepository.findById(paymentDto.getDepositorAccountNum()).get();
            Long money = account.deposit(paymentDto.getMoney());
            TransactionHistory transactionHistory = new TransactionHistory(bankAccount, paymentDto.getDepositorContent(), LocalDateTime.now(), paymentDto.getMoney(), Type.DEPOSIT, money);
            transactionHistoryRepository.save(transactionHistory);
        }catch (Exception e){
            throw new BackAccountException.WithdrawException("입금 중 문제가 발생했습니다!");
        }
    }
}
