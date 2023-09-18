package d209.Idontcare;

import d209.Idontcare.common.exception.MustChildException;
import d209.Idontcare.common.exception.MustParentException;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney.Type;
import d209.Idontcare.pocketmoney.service.PocketMoneyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class RegularPocketTests {
  
  @Autowired
  private PocketMoneyServiceImpl pocketMoneyService;
  
  @Autowired
  private TUserRepository TUserRepository;
  
  @Test
  @DisplayName("다음 지급일 계산 테스트")
  void nextCycle() {
    int year = 2023;
    int month = 9;
    int day = 13;
    int hour = 0;
    int minute = 1;
    
    LocalDateTime now = LocalDateTime.of(year, month, day, hour, minute);
    
    //매일 테스트
    assert pocketMoneyService.getNextDueDate(now, Type.DAY, null).equals(230914);
    
    //주간 테스트
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 4).equals(230914);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 5).equals(230915);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 6).equals(230916);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 7).equals(230917);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 1).equals(230918);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 2).equals(230919);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 3).equals(230920);
    
    //월간 테스트
    assert pocketMoneyService.getNextDueDate(now, Type.MONTH, 1).equals(231001);
    assert pocketMoneyService.getNextDueDate(now, Type.MONTH, 12).equals(231012);
    assert pocketMoneyService.getNextDueDate(now, Type.MONTH, 30).equals(230930);
    assert pocketMoneyService.getNextDueDate(now, Type.MONTH, 31).equals(230930);
    
    year = 2023;
    month = 12;
    day = 26;   //화요일
    hour = 0;
    minute = 1;
    
    now = LocalDateTime.of(year, month, day, hour, minute);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 3).equals(231227);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 4).equals(231228);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 5).equals(231229);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 6).equals(231230);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 7).equals(231231);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 1).equals(240101);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 2).equals(240102);
    
    year = 2023;
    month = 12;
    day = 31;
    hour = 0;
    minute = 1;
    
    now = LocalDateTime.of(year, month, day, hour, minute);
    assert pocketMoneyService.getNextDueDate(now, Type.DAY, null).equals(240101);
    
    
    year = 2024;
    month = 2;
    day = 28;   //2.28(수) 다음은 2.29이다
    hour = 0;
    minute = 1;
    
    now = LocalDateTime.of(year, month, day, hour, minute);
    assert pocketMoneyService.getNextDueDate(now, Type.DAY, null).equals(240229);
    
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 4).equals(240229);
    assert pocketMoneyService.getNextDueDate(now, Type.WEEK, 5).equals(240301);
    
  }
  
  @Test
  @DisplayName("정기 용돈 등록 테스트")
  void registRegularPocketMoney(){
    TUser parent = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.PARENT).toList().get(0);
    TUser child = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.CHILD).toList().get(0);
    
    /* 정상 요청 처리 */
    RegistRegularPocketMoneyReqDto req = RegistRegularPocketMoneyReqDto.builder()
            .type(Type.DAY)
            .childUserId(child.getUserId())
            .cycle(null)
            .amount(1000)
            .build();
    RegularPocketMoney saved = pocketMoneyService.registryRegularPocketMoney(parent, req, LocalDateTime.now());
    
    assert saved.getRegularPocketMoneyId().equals(1L);
    
    /* 대상이 아이가 아닌 경우 -> 에러가 발생해야한다 */
    req = RegistRegularPocketMoneyReqDto.builder()
        .type(Type.DAY)
        .childUserId(parent.getUserId())
        .cycle(null)
        .amount(1000)
        .build();
    
    RegistRegularPocketMoneyReqDto finalReq = req;
    Assertions.assertThrows(MustChildException.class, () -> pocketMoneyService.registryRegularPocketMoney(parent, finalReq, LocalDateTime.now()));
    
    /* 부모가 아닌 사람이 요청 한 경우 -> 에러가 발생해야한다 */
    Assertions.assertThrows(MustParentException.class, () -> pocketMoneyService.registryRegularPocketMoney(child, finalReq, LocalDateTime.now()));
  }
  
  
}
