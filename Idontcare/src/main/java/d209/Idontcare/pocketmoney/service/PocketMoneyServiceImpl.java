package d209.Idontcare.pocketmoney.service;

import d209.Idontcare.account.dto.req.VirtualToVirtualReq;
import d209.Idontcare.account.entity.Type;
import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.account.service.VirtualAccountService;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.pocketmoney.dto.req.*;
import d209.Idontcare.pocketmoney.dto.res.*;
import d209.Idontcare.pocketmoney.entity.PocketMoneyRequest;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import d209.Idontcare.pocketmoney.repository.PocketMoneyRequestRepository;
import d209.Idontcare.pocketmoney.repository.RegularPocketMoneyRepository;
import d209.Idontcare.relationship.service.RelationshipService;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import d209.Idontcare.user.service.UserService;
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
  
  private final UserService userService;
  private final UserRepository userRepository;
  private final RegularPocketMoneyRepository regularPocketMoneyRepository;
  private final PocketMoneyRequestRepository pocketMoneyRequestRepository;
  private final RelationshipService relationshipService;
  private final VirtualAccountService virtualAccountService;
  
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
  public List<GetRegularPocketMoneysResDto> getRegularPocketMoneys(Long parentUserId) {
    List<Tuple> tuples = regularPocketMoneyRepository.findAllByParentUserId(parentUserId);
    
    return tuples.stream().map(GetRegularPocketMoneysResDto::new).toList();
  }
  
  @Override
  public RegularPocketMoney registryRegularPocketMoney(Long parentUserId, RegistRegularPocketMoneyReqDto req, LocalDateTime now) throws CommonException {
    
    User child = userService.findByUserId(req.getChildUserId()).orElseThrow(() -> new NoSuchUserException("해당하는 자녀를 찾을 수 없습니다"));
    
    if( !child.isChild() ) throw new MustChildException("대상이 아이가 아닙니다");
    
    if( !relationshipService.relationExistsByParentAndChild(parentUserId, child.getUserId()) ) throw new BadRequestException("부모와 자식간의 관계가 아닙니다");
    
    Integer cycle = req.getCycle();
    if(req.getType() == RegularPocketMoney.Type.DAY && (cycle == null || cycle != 1)) throw new BadRequestException("DAY타입에는 주기가 1이어야 합니다");
    if(req.getType() == RegularPocketMoney.Type.WEEK){
      if( !(cycle >= 1 && cycle <= 7) ) throw new BadRequestException("WEEK타입에는 1 ~ 7 사이의 주기만 정할 수 있습니다");
    }
    if(req.getType() == RegularPocketMoney.Type.MONTH) {
      if( !(cycle >= 1 && cycle <= 31) ) throw new BadRequestException("MONTH타입에는 1 ~ 31 사이의 주기만 정할 수 있습니다");
    }
    
    //지급되어야 할 날짜 계산
    Integer dueDate = getNextDueDate(now, req.getType(), req.getCycle());
    
    User parent = userRepository.getReferenceById(parentUserId);
    
    
    RegularPocketMoney regularPocketMoney = RegularPocketMoney.builder()
                                            .parent(parent)
                                            .child(userRepository.getReferenceById(req.getChildUserId()))
                                            .type(req.getType())
                                            .cycle(req.getCycle())
                                            .dueDate(dueDate)
                                            .amount(req.getAmount())
                                            .build();
    
    return regularPocketMoneyRepository.save(regularPocketMoney);
  }
  
  @Override
  public void sendPocketMoney(Long parentUserId, SendPocketMoneyReqDto req){
    
    if( !userRepository.existsById(req.getChildUserId()) ){
      throw new NoSuchUserException("해당 자녀를 찾을 수 없습니다");
    }
    
    Long parentVirtualAccountId = virtualAccountService.userAccount(parentUserId);  //부모　가상계좌　아이디　받기
    VirtualToVirtualReq trnasReq = VirtualToVirtualReq
        .builder()
        .money(req.getAmount())
        .userId(req.getChildUserId())
        .content(req.getComment())
        .type(Type.POCKET)
        .build();
    
    try{
      virtualAccountService.virtualPayment(trnasReq, parentVirtualAccountId);
    } catch(VirtualAccountException e){
      //돈 부족
      throw new VirtualAccountException("돈 부족");
    }
  }
  
  @Override
  public void requestPocketMoney(Long childUserId, RequestPocketMoneyReqDto req) throws MustParentException, MustChildException {
    User parent = userService.findByUserId(req.getParentUserId()).orElseThrow(() -> new NoSuchUserException("해당 부모를 찾을 수 없습니다"));
    
    LocalDateTime now = LocalDateTime.now();
    now = now.plusDays(2);
    Integer cancelDate = (now.getYear() % 100) * 10_000 + now.getMonthValue() * 100 + now.getDayOfMonth();
    
    User child = userRepository.getReferenceById(childUserId);
    
    PocketMoneyRequest request = PocketMoneyRequest.builder()
        .parent(parent)
        .child(child)
        .amount(req.getAmount())
        .content(req.getContent())
        .type(PocketMoneyRequest.Type.REQUEST)
        .cancelDate(cancelDate)
        .build();
    
    pocketMoneyRequestRepository.save(request);
  }
  
  @Override
  public List<GetPocketMoneyRequestResDto> getPocketMoneyRequest(Long userId) throws MustParentException {
    User user = userRepository.getReferenceById(userId);
    
    if(user.isParent()) return pocketMoneyRequestRepository.findAllByParent(user).stream().map(GetPocketMoneyRequestResDto::new).toList();
    else return pocketMoneyRequestRepository.findAllByChild(user).stream().map(GetPocketMoneyRequestResDto::new).toList();
  }
  
  @Override
  public void processPocketMoneyRequest(Long parentUserId, ProcessPocketMoneyRequestReqDto req){
    User parent = userRepository.getReferenceById(parentUserId);
    
    PocketMoneyRequest pocketMoneyRequest = pocketMoneyRequestRepository.findById(req.getPocketMoneyRequestId())
        .orElseThrow(() -> new NoSuchContentException("해당하는 요청을 찾을 수 없습니다"));
    
    if( !parent.getUserId().equals(pocketMoneyRequest.getParent().getUserId()) ){
      //만약 해당 요청에 대해 권한이 없으면
      throw new AuthorizationException();
    }
    
    if(req.getType() == ProcessPocketMoneyRequestReqDto.Type.ACCEPT){
      //수락이면
      Long parentVirtualAccountId = virtualAccountService.userAccount(parentUserId);  //부모　가상계좌　아이디　받기
      VirtualToVirtualReq trnasReq = VirtualToVirtualReq
          .builder()
          .money(Long.valueOf(pocketMoneyRequest.getAmount()))
          .userId(pocketMoneyRequest.getChild().getUserId())
          .content(String.format("%s님의 용돈 지급", parent.getName()))
          .type(Type.POCKET)
          .build();
      try{
        virtualAccountService.virtualPayment(trnasReq, parentVirtualAccountId);
      } catch(VirtualAccountException e){
        throw new VirtualAccountException("돈 부족");
      }
      pocketMoneyRequest.setType(PocketMoneyRequest.Type.ACCEPTED);
    }
    else{
      //거절이면
      pocketMoneyRequest.setType(PocketMoneyRequest.Type.REJECT);
    }
  }
}
