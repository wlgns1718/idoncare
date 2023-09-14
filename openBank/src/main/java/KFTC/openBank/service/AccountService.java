package KFTC.openBank.service;


import KFTC.openBank.dto.*;
import KFTC.openBank.exception.AccountException;
import KFTC.openBank.repository.AccountRepository;
import KFTC.openBank.repository.BankAccountRepository;
import KFTC.openBank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final BankAccountRepository bankAccountRepository;

    //플랫폼에 등록된 계좌에서 잔액 조회
    @Transactional(readOnly = true)
    public BalanceResponseDto findBalance(String pinNumber) throws AccountException {
        Tuple result = accountRepository.findBankAndAccountNumberById(pinNumber);
        if(result==null){
           throw new AccountException.FintechNumNotFoundException("요청 하신 핀테크 이용 번호는 등록되어 있지 않습니다.");
        }
        //은행 코드 및 계좌 번호
        String bankId = (String) result.get(0);
        Long accountNumber = (Long) result.get(1);
        //은행 이름
        String bankName = bankRepository.findNameById(bankId);
        Long money = bankAccountRepository.findMoneyById(accountNumber);
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
    public WithdrawReponseDto withdraw(WithdrawRequestDto withdrawRequestDto) throws AccountException {
        Tuple result = accountRepository.findBankAndAccountNumberById(withdrawRequestDto.getFintechUseNum());
        if(result==null){
            throw new AccountException.FintechNumNotFoundException("요청 하신 핀테크 이용 번호는 등록되어 있지 않습니다.");
        }
        //입금 하고자 하는 계좌의 소유자
        String depositName = bankAccountRepository.findNameByIdAndBankId(withdrawRequestDto.getCntrAccountNum(), withdrawRequestDto.getCntrAccountBankCodeStd());
        if(depositName.equals(null)){
            throw new AccountException.AccoutNotFoundException("입금을 원하는 계좌는 없는 계좌입니다.");
        }
        //은행 코드 및 계좌 번호
        String bankId = (String) result.get(0);
        Long accountNumber = (Long) result.get(1);
        //은행 이름
        String bankName = bankRepository.findNameById(bankId);
        Long money = bankAccountRepository.findMoneyById(accountNumber);
    }
}
