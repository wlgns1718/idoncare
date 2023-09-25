package d209.Idontcare.common.service;

import d209.Idontcare.account.entity.RealAccount;
import d209.Idontcare.account.entity.TransactionHistory;
import d209.Idontcare.account.entity.Type;
import d209.Idontcare.account.entity.VirtualAccount;
import d209.Idontcare.account.repository.RealAccountRepository;
import d209.Idontcare.account.repository.TransactionHistoryRepository;
import d209.Idontcare.account.repository.VirtualAccountRepository;
import d209.Idontcare.account.service.EncryptService;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.entity.UserDetail;
import d209.Idontcare.user.repository.UserDetailRepository;
import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class InitService {
  
  private final UserRepository userRepository;
  private final UserDetailRepository userDetailRepository;
  private final VirtualAccountRepository virtualAccountRepository;
  private final TransactionHistoryRepository transactionHistoryRepository;
  private final RealAccountRepository realAccountRepository;
  
  private final EncryptService encryptService;
  
  @PostConstruct
  public void init(){
    userDataInit();
    virtualAccountDataInit();
    transactionHistoryDataInit();
    realAccountDataInit();
    
  }
  
  private void userDataInit(){
    if(userRepository.count() > 0) return;
    for(long i = 1L; i <= 9; i++){
      User parent = new User(  i, i, "010" + "1234" + "000" + i, "김부모" + i, Role.PARENT, "김부모" + i + "_닉네임") ;
      parent.setUUID();
      User savedParent = userRepository.save(parent);
      UserDetail parentDetail = new UserDetail(savedParent.getUserId(), savedParent, "199101" + i, "mail" + i + "@naver.com");
      userDetailRepository.save(parentDetail);
      
      User child = new User(10L + i, 10L + i, "010" + "4321" + "001" + i, "김자식" + i, Role.CHILD, "김자식" + i + "_닉네임");
      child.setUUID();
      User savedChild = userRepository.save(child);
      UserDetail childDetail = new UserDetail(savedChild.getUserId(), savedChild, "200101" + i, "mail" + i + "@gmail.com");
      userDetailRepository.save(childDetail);
    }
  }
  
  private void virtualAccountDataInit(){
    if(virtualAccountRepository.count() > 0) return;
    String pw = encryptService.encrypt("123456");
    
    for(long i = 1L; i <= 9; i++){
      VirtualAccount parent = new VirtualAccount(userRepository.findByKakaoId(i).get(), 1_000_000L * i, pw);
      virtualAccountRepository.save(parent);
      
      VirtualAccount child = new VirtualAccount(userRepository.findByKakaoId(10L + i).get(), 1_000_000L * i, pw);
      virtualAccountRepository.save(child);
    }
  }
  
  private void transactionHistoryDataInit(){
    if(transactionHistoryRepository.count() > 0) return;
    
    for(long i = 1L; i <= 5; i++){
      TransactionHistory depository = new TransactionHistory(userRepository.findByKakaoId(i).get(), "스타벅스 결제1", 10_000L, Type.DEPOSITORY, 100_000L);
      TransactionHistory withdrawal = new TransactionHistory(userRepository.findByKakaoId(i).get(), "스타벅스 결제2", 10_000L, Type.WITHDRAWAL, 100_000L);
      transactionHistoryRepository.save(depository);
      transactionHistoryRepository.save(withdrawal);
      
      depository = new TransactionHistory(userRepository.findByKakaoId(10L + i).get(), "스타벅스 결제1", 10_000L, Type.DEPOSITORY, 100_000L);
      withdrawal = new TransactionHistory(userRepository.findByKakaoId(10L + i).get(), "스타벅스 결제2", 10_000L, Type.WITHDRAWAL, 100_000L);
      transactionHistoryRepository.save(depository);
      transactionHistoryRepository.save(withdrawal);
    }
  }
  
  private void realAccountDataInit(){
    if(realAccountRepository.count() > 0) return;
    String pw = encryptService.encrypt("123456");
    
    for(long i = 1L; i <= 5; i++){
      RealAccount account = new RealAccount(encryptService.encrypt(String.valueOf(i)), userRepository.findByKakaoId(i).get(), pw, "신한은행", "41");
      realAccountRepository.save(account);
    }
  }
}
