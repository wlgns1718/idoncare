package d209.Idontcare.common.service;

import d209.Idontcare.account.entity.*;
import d209.Idontcare.account.repository.RealAccountRepository;
import d209.Idontcare.account.repository.TransactionHistoryRepository;
import d209.Idontcare.account.repository.VirtualAccountRepository;
import d209.Idontcare.account.service.EncryptService;
import d209.Idontcare.mission.entity.Mission;
import d209.Idontcare.mission.repository.MissionRepository;
import d209.Idontcare.relationship.entity.Relationship;
import d209.Idontcare.relationship.repository.RelationshipRepository;
import d209.Idontcare.relationship.repository.RelationshipRequestRepository;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.entity.UserDetail;
import d209.Idontcare.user.repository.UserDetailRepository;
import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class InitService {

  private final UserRepository userRepository;
  private final UserDetailRepository userDetailRepository;
  private final VirtualAccountRepository virtualAccountRepository;
  private final TransactionHistoryRepository transactionHistoryRepository;
  private final RealAccountRepository realAccountRepository;
  private final RelationshipRepository relationshipRepository;
  private final RelationshipRequestRepository relationshipRequestRepository;
  private final MissionRepository missionRepository;
  private final EncryptService encryptService;

  @PostConstruct
  public void init(){
    userDataInit();
    virtualAccountDataInit();
    transactionHistoryDataInit();
    realAccountDataInit();
    relationshipDataInit();
    relationshipRequestDataInit();
    MissionDataInit();
  }

  private void userDataInit(){
    if(userRepository.count() > 0) return;
    for(long i = 1L; i <= 2; i++) {
      User parent = new User(i, i, "010" + "1234" + "567" + ((i + 7) % 10), "김부모" + i, Role.PARENT, "김부모" + i + "_닉네임");
      parent.setUUID();
      User savedParent = userRepository.save(parent);
      UserDetail parentDetail = new UserDetail(savedParent.getUserId(), savedParent, "1990010" + i, "mail" + i + "@naver.com");
      userDetailRepository.save(parentDetail);
    }
    for(long i = 3L; i <= 5; i++) {
      User child = new User(i, i, "010" + "4321" + "001" + i, "김자식" + i, Role.CHILD, "김자식" + i + "_닉네임");
      child.setUUID();
      User savedChild = userRepository.save(child);
      UserDetail childDetail = new UserDetail(savedChild.getUserId(), savedChild, "2000010" + i, "mail" + i + "@gmail.com");
      userDetailRepository.save(childDetail);
    }
    //스타벅스?
//    User child = new User(0L, 0L, "010" + "0000" + "0000", "StarBucks" , Role.PARENT, "스타벅스");
//    child.setUUID();
//    User savedChild = userRepository.save(child);
//    UserDetail childDetail = new UserDetail(savedChild.getUserId(), savedChild, "2000010" + i, "mail" + i + "@gmail.com");
//    userDetailRepository.save(childDetail);
  }

  private void virtualAccountDataInit(){
    if(virtualAccountRepository.count() > 0) return;
    String pw = encryptService.encrypt("123456");
    for(long i = 1L; i <= 5; i++){
      VirtualAccount parent = new VirtualAccount(userRepository.findByKakaoId(i).get(), 1_000_000L * i, pw);
      virtualAccountRepository.save(parent);
    }
  }

  private void transactionHistoryDataInit(){
    if(transactionHistoryRepository.count() > 0) return;

    TransactionHistory depository = new TransactionHistory(userRepository.findByKakaoId(1L).get(), LocalDateTime.now(), "스타벅스 결제", 10_000L, Type.PAYMENT,CashFlow.WITHDRAWAL, 100_000L);
    TransactionHistory withdrawal = new TransactionHistory(userRepository.findByKakaoId(1L).get(), LocalDateTime.now(), "충전 계좌에서 가상 계좌로 입금", 10_000L, Type.PAYMENT,CashFlow.DEPOSIT, 100_000L);
    transactionHistoryRepository.save(depository);
    transactionHistoryRepository.save(withdrawal);

    depository = new TransactionHistory(userRepository.findByKakaoId(3L).get(), LocalDateTime.now(), "장난감 결제", 10_000L, Type.PAYMENT, CashFlow.WITHDRAWAL, 100_000L);
    transactionHistoryRepository.save(depository);

    withdrawal = new TransactionHistory(userRepository.findByKakaoId(3L).get(), LocalDateTime.now().minusDays(1), "용돈", 10_000L, Type.POCKET, CashFlow.DEPOSIT, 100_000L);
    transactionHistoryRepository.save(withdrawal);

    withdrawal = new TransactionHistory(userRepository.findByKakaoId(3L).get(), LocalDateTime.now().minusDays(2), "미션비", 10_000L, Type.MISSION, CashFlow.DEPOSIT, 100_000L);
    transactionHistoryRepository.save(withdrawal);

    for(long i = 3; i <= 5; i++){
      //아이들 결제내역 만들어주기
      long current = 1_000_000;
      for(int j = 0; j <= 7; j++) {
        depository = new TransactionHistory(userRepository.findByKakaoId(i).get(), LocalDateTime.now().minusDays(j), "장난감" + j, 1_000L * (j+1), Type.PAYMENT, CashFlow.DEPOSIT, current);
        transactionHistoryRepository.save(depository);
        current -= 1_000L * (j+1);

        depository = new TransactionHistory(userRepository.findByKakaoId(i).get(), LocalDateTime.now().minusDays(j), "장난감 두번사기" + j, 500L * (j+1), Type.PAYMENT, CashFlow.DEPOSIT, current);
        transactionHistoryRepository.save(depository);
        current -= 500L * (j+1);

        withdrawal = new TransactionHistory(userRepository.findByKakaoId(i).get(), LocalDateTime.now().minusDays(j), "부모님으로부터 용돈" + j, 1_200L * (j+1), Type.POCKET, CashFlow.WITHDRAWAL, current);
        transactionHistoryRepository.save(withdrawal);
        current += 1_200L * (j+1);
        
        depository = new TransactionHistory(userRepository.findByKakaoId(i).get(), LocalDateTime.now().minusMonths(j), "장난감" + j, 600L * (j+1), Type.PAYMENT, CashFlow.DEPOSIT, current);
        transactionHistoryRepository.save(depository);
        current -= 1_000L * (j+1);

        depository = new TransactionHistory(userRepository.findByKakaoId(i).get(), LocalDateTime.now().minusMonths(j), "장난감 두번사기" + j, 800L * (j+1), Type.PAYMENT, CashFlow.DEPOSIT, current);
        transactionHistoryRepository.save(depository);
        current -= 500L * (j+1);

        withdrawal = new TransactionHistory(userRepository.findByKakaoId(i).get(), LocalDateTime.now().minusMonths(j), "부모님으로부터 용돈" + j, 800L * (j+1), Type.POCKET, CashFlow.WITHDRAWAL, current);
        transactionHistoryRepository.save(withdrawal);
        current += 1_200L * (j+1);
      }
    }
  }

  private void realAccountDataInit(){
    if(realAccountRepository.count() > 0) return;
    for(long i = 1L; i <= 5; i++){
      String pw = encryptService.encrypt("123451"+ i);
      RealAccount account = new RealAccount(encryptService.encrypt(String.valueOf("1111111"+i)), userRepository.findByKakaoId(i).get(), pw, "신한은행", "41");
      realAccountRepository.save(account);
    }
  }

  private void relationshipDataInit(){
    if(relationshipRepository.count() > 0) return;
    User parent1 = userRepository.findByKakaoId(1L).get();
    User child1 = userRepository.findByKakaoId(3L).get();
    User child2 = userRepository.findByKakaoId(4L).get();

    relationshipRepository.save(new Relationship(parent1, child1));
    relationshipRepository.save(new Relationship(parent1, child2));
  }

  private void relationshipRequestDataInit(){
//    if(relationshipRequestRepository.count() > 0) return;
//    User parent1 = userRepository.findByKakaoId(2L).get();
//    User child1 = userRepository.findByKakaoId(5L).get();
//
//    relationshipRequestRepository.save(new RelationshipRequest(parent1, child1));
  }
  private void MissionDataInit(){
    User parent = userRepository.findByKakaoId(1L).get();
    User child  = userRepository.findByKakaoId(3L).get();
    for(Long i = 0L; i < 5L; i++){
      if(i == 2L){
        Mission mission = new Mission(parent,child, "심부름 하기",10000, d209.Idontcare.mission.entity.Type.UNPAID,
                "열심히 할게요","미션 완료했습니다.",LocalDateTime.now());
        missionRepository.save(mission);
        continue;
      }
      Mission mission = new Mission(parent,child,"방청소하기" + i, i*1000,
              d209.Idontcare.mission.entity.Type.PROCESS,"열심히 할게요" + i,null,null);
      missionRepository.save(mission);
    }

  }
}
