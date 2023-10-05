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
    bankRepository.save(new Bank("12", "대구은행", "/images/대구은행.png"));
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
    bankRepository.save(new Bank("11", "KB은행", "/images/KB은행.png"));
    bankRepository.save(new Bank("91", "NH농협", "/images/NH농협.png"));
    bankRepository.save(new Bank("34", "Sh수협은행", "/images/Sh수협은행.png"));
  }

  public void bankAccountDataInit(){
    if(bankAccountRepository.count() > 0) return;
    Bank bank41 = bankRepository.getReferenceById("41");
    Bank bank12 = bankRepository.getReferenceById("12");
    Bank bank11 = bankRepository.getReferenceById("11");
    bankAccountRepository.save(new BankAccount("40660204210915", bank11, 10000L, "이정훈", "19980502"));
    bankAccountRepository.save(new BankAccount("110409773220", bank41, 100000L, "성연석", "19951006"));
    bankAccountRepository.save(new BankAccount("24913128600", bank12, 10000L, "이우철", "19980604"));
    bankAccountRepository.save(new BankAccount("55555555", bank11, 1000000L, "김슬기", "19980413"));
    bankAccountRepository.save(new BankAccount("00000000", bank41, 1000000000000L, "아이돈케어", "202309011"));
    bankAccountRepository.save(new BankAccount("99999999", bank41, 1000000000000L, "starbucks", "202309011"));
    for(int i = 1; i <= 2; i++){
      bankAccountRepository.save(new BankAccount("1111111"+i, bank41, 1000000L, "김부모" + i, "1990010" + i));
    }
    for(int i = 3; i <= 5; i++){
      bankAccountRepository.save(new BankAccount("2222222" + i, bank41, 1000L, "김자식" + i, "2000010" + i));
    }
  }

  public void userDataInit(){
    if(userRepository.count() > 0) return;
    userRepository.save(new User("아이돈케어", "01000000000", Role.CORPORATION));
  }
  
  public void finTechServiceDataInit(){
    if(finTechServiceRepository.count() > 0) return;
    finTechServiceRepository.save(new FinTechService("1234512345", "아이돈케어", "idontcare", "1234", "12u4hi1b245hj124", "123ijn4u123h5bkjn", "http://127.0.0.1/test"));
  }
  
  public void accountDataInit(){
//    if(accountRepository.count() > 0) return;
//    accountRepository.save(new Account("1234512345",
//        bankRepository.getReferenceById("41"),
//        userRepository.getReferenceById(1L),
//        "1111111111",
//        finTechServiceRepository.getReferenceById("1234512345")));
  }
  
  public void mobileDataInit(){
    if(mobileRepository.count() > 0) return;
    for(int i = 1; i <= 2; i++){
      mobileRepository.save(new Mobile("0101234567" + i, "김부모" + i, "1990010" + i, MobileSort.SK));
    }
    for(int i = 3; i <= 5; i++){
      mobileRepository.save(new Mobile("0104321001" + i, "김자식" + i, "2000010"+i, MobileSort.SK));
    }
    mobileRepository.save(new Mobile("01038300631", "이정훈", "19980502", MobileSort.LG));
    mobileRepository.save(new Mobile("01030715123", "성연석", "19951006", MobileSort.KT));
    mobileRepository.save(new Mobile("01031579908", "이우철", "19980604", MobileSort.SK));
    mobileRepository.save(new Mobile("01050321051", "김슬기", "19980413", MobileSort.KT));
  }
}
