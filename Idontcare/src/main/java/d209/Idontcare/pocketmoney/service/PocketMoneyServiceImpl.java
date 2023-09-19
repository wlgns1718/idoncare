package d209.Idontcare.pocketmoney.service;

import d209.Idontcare.TUser;
import d209.Idontcare.TUserRepository;
import d209.Idontcare.TUserService;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.MustChildException;
import d209.Idontcare.common.exception.MustParentException;
import d209.Idontcare.common.exception.NoSuchUserException;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.req.RequestPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.req.SendPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.res.GetPocketMoneyRequestResDto;
import d209.Idontcare.pocketmoney.entity.PocketMoneyRequest;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import d209.Idontcare.pocketmoney.repository.PocketMoneyRequestRepository;
import d209.Idontcare.pocketmoney.repository.RegularPocketMoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PocketMoneyServiceImpl implements PocketMoneyService {
  
  private final TUserService TUserService;
  private final TUserRepository TUserRepository;
  private final RegularPocketMoneyRepository regularPocketMoneyRepository;
  private final PocketMoneyRequestRepository pocketMoneyRequestRepository;
  
  //다음 지급 예정일에 대해 계산
  public Integer getNextDueDate(LocalDateTime now, RegularPocketMoney.Type type, Integer cycle){
    Integer result = 0;
    LocalDateTime next = null;
    if(type == RegularPocketMoney.Type.DAY){
      //매일 이면
      next = now.plusDays(1);
    }
    else if(type == RegularPocketMoney.Type.WEEK){
      //주 단위 이면
      next = now.with(TemporalAdjusters.next(DayOfWeek.of(cycle))); //가장 가까운 cycle요일의 날을 찾는다
    }
    else if(type == RegularPocketMoney.Type.MONTH){
      //달 단위 이면
      int lastDay = YearMonth.from(now).atEndOfMonth().getDayOfMonth(); //해당 달의 마지막 날 가져오기
      
      int calcedCycle = Math.min(lastDay, cycle); //더 작은 값으로 변경
      
      if(now.getDayOfMonth() < calcedCycle){
        //오늘이 계산된 cycle보다 작으면
        next = now.withDayOfMonth(calcedCycle);    //이번달의 해당 날짜로 설정
      }
      else{
        //오늘이 계산된 cycle이상이면
        next = now.plusMonths(1);   //다음달로 가고
        int nextLastDay = YearMonth.from(next).atEndOfMonth().getDayOfMonth();  //다음 달의 마지막날 가져오고
        next = next.withDayOfMonth(Math.min(nextLastDay, cycle));                   //다음달의 다시 계산된 값으로 변경
      }
    }
    
    
    result += (next.getYear() % 100) * 10_000;
    result += next.getMonthValue() * 100;
    result += next.getDayOfMonth();
    
    return result;
  }
  
  @Override
  public RegularPocketMoney registryRegularPocketMoney(TUser parent, RegistRegularPocketMoneyReqDto req, LocalDateTime now) throws CommonException {
    
    if(parent.getType() != TUser.Type.PARENT) throw new MustParentException("부모만 요청을 처리할 수 있습니다");
    
    TUser child = null;
    try{
      child = TUserService.findByUserId(req.getChildUserId());
    } catch(NoSuchUserException e){
      throw new NoSuchUserException("해당 자녀를 찾을 수 없습니다");
    }
    if(child.getType() != TUser.Type.CHILD) throw new MustChildException("대상이 아이가 아닙니다");
    
    /* TODO : 부모와 아이 간의 관계 확인 필요 */
    
    /* TODO : 정기용돈의 Type과 cycle 간의 유효성 검사 필요 */
    
    //지급되어야 할 날짜 계산
    Integer dueDate = getNextDueDate(now, req.getType(), req.getCycle());
    
    
    RegularPocketMoney regularPocketMoney = RegularPocketMoney.builder()
                                            .parent(parent)
                                            .child(TUserRepository.getReferenceById(req.getChildUserId()))
                                            .type(req.getType())
                                            .cycle(req.getCycle())
                                            .dueDate(dueDate)
                                            .amount(req.getAmount())
                                            .build();
    RegularPocketMoney saved = regularPocketMoneyRepository.save(regularPocketMoney);
    
    return saved;
  }
  
  @Override
  public void sendPocketMoney(TUser parent, SendPocketMoneyReqDto req){
    if(parent.getType() != TUser.Type.PARENT) throw new MustParentException("부모만 요청할 수 있습니다");
    
    TUser child = null;
    try{
      child = TUserService.findByUserId(req.getChildUserId());
    } catch(NoSuchUserException e){
      throw new NoSuchUserException("해당 자녀를 찾을 수 없습니다");
    }
    
    /* TODO : 용돈 지급 필요 */
  }
  
  @Override
  public void requestPocketMoney(TUser child, RequestPocketMoneyReqDto req) throws MustParentException, MustChildException {
    if(child.getType() != TUser.Type.CHILD) throw new MustChildException("아이만 요청할 수 있습니다");
    
    TUser parent = null;
    try{
      parent = TUserService.findByUserId(req.getParentUserId());
    } catch(NoSuchUserException e){
      throw new NoSuchUserException("해당 부모를 찾을 수 없습니다");
    }
    
    PocketMoneyRequest request = PocketMoneyRequest.builder()
        .parent(parent)
        .child(child)
        .amount(req.getAmount())
        .content(req.getContent())
        .type(PocketMoneyRequest.Type.REQUEST)
        .build();
    
    pocketMoneyRequestRepository.save(request);
    
  }
  
  @Override
  public List<GetPocketMoneyRequestResDto> getPocketMoneyRequest(TUser parent) throws MustParentException {
    if(parent.getType() != TUser.Type.PARENT) throw new MustParentException();
    
    List<Tuple> requests = pocketMoneyRequestRepository.findAllByParent(parent);
    System.out.println(requests);
    
    return null;
  }
}
