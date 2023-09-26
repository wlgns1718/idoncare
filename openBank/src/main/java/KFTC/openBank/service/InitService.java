package KFTC.openBank.service;

import KFTC.openBank.domain.*;
import KFTC.openBank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class InitService {
  
  
  private final BankRepository bankRepository;
  private final BankAccountRepository bankAccountRepository;
  private final UserRepository userRepository;
  private final FinTechServiceRepository finTechServiceRepository;
  private final AccountRepository accountRepository;
  private final MobileRepository mobileRepository;
  
  
  @PostConstruct
  public void init(){
    bankDataInit();
    bankAccountDataInit();
    userDataInit();
    finTechServiceDataInit();
    accountDataInit();
    mobileDataInit();
  }
  
  public void bankDataInit(){
    if(bankRepository.count() != 0) return;
    bankRepository.save(new Bank("46", "광주은행", "/images/광주은행.png"));
    bankRepository.save(new Bank("46", "광주은행", "/images/광주은행.png"));
    bankRepository.save(new Bank("71", "롯데카드", "/images/롯데카드.png"));
    bankRepository.save(new Bank("30", "KDB산업은행", "/images/KDB산업은행.png"));
    bankRepository.save(new Bank("31", "BC카드", "/images/BC카드.png"));
    bankRepository.save(new Bank("51", "삼성카드", "/images/삼성카드.png"));
    bankRepository.save(new Bank("38", "새마을금고", "/images/새마을금고.png"));
    bankRepository.save(new Bank("41", "신한은행", "/images/신한은행.png"));
    bankRepository.save(new Bank("62", "신협", "/images/신협.png"));
    bankRepository.save(new Bank("36", "씨티카드", "/images/씨티카드.png"));
    bankRepository.save(new Bank("W1", "우리은행", "/images/우리은행.png"));
    bankRepository.save(new Bank("37", "우체국예금보험", "/images/우체국예금보험.png"));
    bankRepository.save(new Bank("39", "저축은행중앙회", "/images/저축은행중앙회.png"));
    bankRepository.save(new Bank("15", "카카오뱅크", "/images/카카오뱅크.png"));
    bankRepository.save(new Bank("3A", "케이뱅크", "/images/케이뱅크.png"));
    bankRepository.save(new Bank("24", "토스뱅크", "/images/토스뱅크.png"));
    bankRepository.save(new Bank("21", "하나은행", "/images/하나은행.png"));
    bankRepository.save(new Bank("61", "현대카드", "/images/현대카드.png"));
    bankRepository.save(new Bank("11", "KB", "/images/KB은행.png"));
    bankRepository.save(new Bank("91", "NH농협", "/images/NH농협.png"));
    bankRepository.save(new Bank("34", "Sh수협은행", "/images/Sh수협은행.png"));
  }

  public void bankAccountDataInit(){
    if(bankAccountRepository.count() > 0) return;
    Bank bank41 = bankRepository.getReferenceById("41");
    bankAccountRepository.save(new BankAccount("0000000000", bank41, 1000000000000L, "아이돈케어", "202309011"));
    bankAccountRepository.save(new BankAccount("8888888888", bank41, 1000000000000L, "starbucks", "202309011"));
    bankAccountRepository.save(new BankAccount("1111111111", bank41, 1000000L, "김부모", "19900101"));
    bankAccountRepository.save(new BankAccount("9999999999", bank41, 1000000L, "김사람", "20000111"));
  }

  public void userDataInit(){
    if(userRepository.count() > 0) return;
    userRepository.save(new User("아이돈케어", "01000000000", Role.CORPORATION));
    userRepository.save(new User("김부모", "01012345678", Role.INDIVIDUAL));
    userRepository.save(new User("김사람", "01099999999", Role.INDIVIDUAL));
  }
  
  public void finTechServiceDataInit(){
    if(finTechServiceRepository.count() > 0) return;
    finTechServiceRepository.save(new FinTechService("1234512345", "아이돈케어", "idontcare", "1234", "12u4hi1b245hj124", "123ijn4u123h5bkjn", "http://127.0.0.1/test"));
  }
  
  public void accountDataInit(){
    if(accountRepository.count() > 0) return;
    
    accountRepository.save(new Account("1234512345",
        bankRepository.getReferenceById("41"),
        userRepository.getReferenceById(1L),
        "1111111111",
        finTechServiceRepository.getReferenceById("1234512345")));
  }
  
  public void mobileDataInit(){
    if(mobileRepository.count() > 0) return;
    mobileRepository.save(new Mobile("01012345678", "김부모", "19900101", MobileSort.SK));
    mobileRepository.save(new Mobile("01045671234", "김아이", "20000101", MobileSort.KT));
    mobileRepository.save(new Mobile("01099999999", "김사람", "19900101", MobileSort.LG));
  }
}
