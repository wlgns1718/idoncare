package d209.Idontcare.common.service;

import d209.Idontcare.account.entity.*;
import d209.Idontcare.account.repository.RealAccountRepository;
import d209.Idontcare.account.repository.TransactionHistoryRepository;
import d209.Idontcare.account.repository.VirtualAccountRepository;
import d209.Idontcare.account.service.EncryptService;
import d209.Idontcare.relationship.entity.Relationship;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.repository.RelationshipRepository;
import d209.Idontcare.relationship.repository.RelationshipRequestRepository;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.entity.UserDetail;
import d209.Idontcare.user.repository.UserDetailRepository;
import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InitService {

  private final UserRepository userRepository;
  private final UserDetailRepository userDetailRepository;
  private final VirtualAccountRepository virtualAccountRepository;
  private final TransactionHistoryRepository transactionHistoryRepository;
  private final RealAccountRepository realAccountRepository;
  private final RelationshipRepository relationshipRepository;
  private final RelationshipRequestRepository relationshipRequestRepository;

  private final EncryptService encryptService;

  @PostConstruct
  public void init(){
    userDataInit();
    virtualAccountDataInit();
    transactionHistoryDataInit();
    realAccountDataInit();
    relationshipDataInit();
    relationshipRequestDataInit();
  }

  private void userDataInit(){
    if(userRepository.count() > 0) return;
    for(long i = 1L; i <= 9; i++){
      User parent = new User(  i, i, "010" + "1234" + "567" + ((i+7) % 10), "김부모" + i, Role.PARENT, "김부모" + i + "_닉네임") ;
      parent.setUUID();
      User savedParent = userRepository.save(parent);
      UserDetail parentDetail = new UserDetail(savedParent.getUserId(), savedParent, "1990010" + i, "mail" + i + "@naver.com");
      userDetailRepository.save(parentDetail);
      User child = new User(10L + i, 10L + i, "010" + "4321" + "001" + i, "김자식" + i, Role.CHILD, "김자식" + i + "_닉네임");
      child.setUUID();
      User savedChild = userRepository.save(child);
      UserDetail childDetail = new UserDetail(savedChild.getUserId(), savedChild, "2000010" + i, "mail" + i + "@gmail.com");
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
      TransactionHistory depository = new TransactionHistory(userRepository.findByKakaoId(i).get(), LocalDateTime.now(), "스타벅스 결제1", 10_000L, Type.PAYMENT,CashFlow.WITHDRAWAL, 100_000L);
      TransactionHistory withdrawal = new TransactionHistory(userRepository.findByKakaoId(i).get(), LocalDateTime.now(), "스타벅스 결제2", 10_000L, Type.PAYMENT,CashFlow.DEPOSIT, 100_000L);
      transactionHistoryRepository.save(depository);
      transactionHistoryRepository.save(withdrawal);

      depository = new TransactionHistory(userRepository.findByKakaoId(10L + i).get(), LocalDateTime.now().minusDays(8), "스타벅스 결제1", 10_000L, Type.TRANSFER, CashFlow.WITHDRAWAL, 100_000L);
      withdrawal = new TransactionHistory(userRepository.findByKakaoId(10L + i).get(), LocalDateTime.now().minusDays(8), "스타벅스 결제2", 10_000L, Type.TRANSFER, CashFlow.WITHDRAWAL, 100_000L);
      transactionHistoryRepository.save(depository);
      transactionHistoryRepository.save(withdrawal);
    }
  }

  private void realAccountDataInit(){
    if(realAccountRepository.count() > 0) return;
    String pw = encryptService.encrypt("123456");
    for(long i = 1L; i <= 9; i++){
      RealAccount account = new RealAccount(encryptService.encrypt(String.valueOf("1111111"+i)), userRepository.findByKakaoId(i).get(), pw, "신한은행", "41");
      realAccountRepository.save(account);
    }
  }

  private void relationshipDataInit(){
    if(relationshipRepository.count() > 0) return;
    User parent1 = userRepository.findByKakaoId(1L).get();
    User parent2 = userRepository.findByKakaoId(2L).get();
    User parent3 = userRepository.findByKakaoId(3L).get();
    User child1 = userRepository.findByKakaoId(11L).get();
    User child2 = userRepository.findByKakaoId(12L).get();
    User child3 = userRepository.findByKakaoId(13L).get();

    relationshipRepository.save(new Relationship(parent1, child1));
    relationshipRepository.save(new Relationship(parent1, child2));
    relationshipRepository.save(new Relationship(parent1, child3));

    relationshipRepository.save(new Relationship(parent2, child1));
    relationshipRepository.save(new Relationship(parent2, child2));

    relationshipRepository.save(new Relationship(parent3, child1));
  }

  private void relationshipRequestDataInit(){
    if(relationshipRequestRepository.count() > 0) return;
    User parent1 = userRepository.findByKakaoId(1L).get();
    User parent2 = userRepository.findByKakaoId(2L).get();
    User parent3 = userRepository.findByKakaoId(3L).get();

    User child2 = userRepository.findByKakaoId(12L).get();
    User child3 = userRepository.findByKakaoId(13L).get();
    User child4 = userRepository.findByKakaoId(14L).get();

    relationshipRequestRepository.save(new RelationshipRequest(parent1, child4));

    relationshipRequestRepository.save(new RelationshipRequest(parent2, child3));
    relationshipRequestRepository.save(new RelationshipRequest(parent2, child4));

    relationshipRequestRepository.save(new RelationshipRequest(parent3, child2));
    relationshipRequestRepository.save(new RelationshipRequest(parent3, child3));
    relationshipRequestRepository.save(new RelationshipRequest(parent3, child4));
  }
}
