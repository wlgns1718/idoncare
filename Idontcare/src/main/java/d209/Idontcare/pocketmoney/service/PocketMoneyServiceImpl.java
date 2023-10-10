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
import d209.Idontcare.pocketmoney.entity.RegularPocketMoneyRejected;
import d209.Idontcare.pocketmoney.repository.*;
import d209.Idontcare.relationship.service.RelationshipService;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import d209.Idontcare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PocketMoneyServiceImpl implements PocketMoneyService {
  
  private final UserService userService;
  private final UserRepository userRepository;
  private final RegularPocketMoneyRepository regularPocketMoneyRepository;
  private final RegularPocketMoneyRejectedRepository regularPocketMoneyRejectedRepository;
  private final PocketMoneyRequestRepository pocketMoneyRequestRepository;
  
  
  private final RelationshipService relationshipService;
  private final VirtualAccountService virtualAccountService;
  
  @Autowired
  public PocketMoneyServiceImpl(UserService userService,
                                UserRepository userRepository,
                                RegularPocketMoneyRepository regularPocketMoneyRepository,
                                RegularPocketMoneyRejectedRepository regularPocketMoneyRejectedRepository,
                                PocketMoneyRequestRepository pocketMoneyRequestRepository,
                                @Lazy RelationshipService relationshipService,
                                @Lazy VirtualAccountService virtualAccountService) {
    this.userService = userService;
    this.userRepository = userRepository;
    this.regularPocketMoneyRepository = regularPocketMoneyRepository;
    this.regularPocketMoneyRejectedRepository = regularPocketMoneyRejectedRepository;
    this.pocketMoneyRequestRepository = pocketMoneyRequestRepository;
    this.relationshipService = relationshipService;
    this.virtualAccountService = virtualAccountService;
  }
  
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
  public List<GetRegularPocketMoneysResDto> getRegularPocketMoneys(Long userId, Role role) {
    List<Tuple> tuples = new LinkedList<>();
    if(role == Role.PARENT){
      tuples = regularPocketMoneyRepository.findAllByParentUserId(userId);
    }
    else if(role == Role.CHILD){
      tuples = regularPocketMoneyRepository.findAllByChildUserId(userId);
    }
    
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
  
  @Override
  public void deleteRegularPocketMoney(Long parentUserId, DeleteRegularPocketMoneyReqDto req) {
    
    RegularPocketMoney regularPocketMoney = regularPocketMoneyRepository.findById(req.getRegularPocketMoneyId()).orElseThrow(NoSuchContentException::new);
    if( !regularPocketMoney.getParent().getUserId().equals(parentUserId) ) throw new AuthorizationException("접근 권한이 없습니다");
    
    regularPocketMoneyRepository.delete(regularPocketMoney);
  }
  

  @Override
  public void executeRegularPocketMoney(LocalDateTime now) {
    int year = now.getYear() % 100;
    int month = now.getMonthValue();
    int day = now.getDayOfMonth();
    
    Integer curr = year * 10_000 + month * 100 + day;
    
    List<RegularPocketMoney> result = regularPocketMoneyRepository.findAllByDueDate(curr);
    
    for(RegularPocketMoney r: result){
      //각각의 처리되어야 하는 정기용돈에 대해
      VirtualToVirtualReq req = VirtualToVirtualReq.builder()
          .userId(r.getChild().getUserId())         //아이가 받을 예정
          .content(curr + "정기용돈")
          .money(r.getAmount().longValue())
          .type(Type.POCKET)
          .build();
      Long parentVirtualAcdountId =
          virtualAccountService.userAccount(r.getParent().getUserId());
      
      //다음 지급날짜로 바꿔주기
      Integer nextDueDate = this.getNextDueDate(now, r.getType(), r.getCycle());
      r.setDueDate(nextDueDate);
      
      try{
        virtualAccountService.virtualPayment(req, parentVirtualAcdountId);
      } catch(VirtualAccountException e){
        //돈 부족일 경우
        // -> Rejected로 정기용돈 미입금내역으로 넣어주자
        RegularPocketMoneyRejected rejected = RegularPocketMoneyRejected.builder()
            .regularPocketMoney(regularPocketMoneyRepository.getReferenceById(r.getRegularPocketMoneyId()))
            .parent(userRepository.getReferenceById(r.getParent().getUserId()))
            .child(userRepository.getReferenceById(r.getChild().getUserId()))
            .amount(r.getAmount())
            .dueDate(curr)
            .build();
        regularPocketMoneyRejectedRepository.save(rejected);
      }
    }
  }
  
  @Transactional(readOnly = true)
  @Override
  public List<GetRegularPocketMoneyRejectedResDto> getRegularPocketMoneyRejectedList(Long parentUserId, Long regularPocketMoneyId) {
    RegularPocketMoney regularPocketMoney = regularPocketMoneyRepository.findById(regularPocketMoneyId).orElseThrow(NoSuchContentException::new);
    
    if( !regularPocketMoney.getParent().getUserId().equals(parentUserId) ) throw new AuthorizationException();
    
    return regularPocketMoneyRejectedRepository.findByRegularPocketMoneyId(regularPocketMoneyId)
        .stream().map(GetRegularPocketMoneyRejectedResDto::new).toList();
  }

  @Override
  public void processRegularPocketMoneyRejected(Long parentUserId, ProcessPcoketMoneyRejectedReqDto req) {
    RegularPocketMoneyRejected rejected = regularPocketMoneyRejectedRepository.findById(req.getRegularPocketMoneyRejectedId())
        .orElseThrow(() -> new NoSuchContentException("존재하지 않는 미입금내역입니다"));
    
    if( !rejected.getParent().getUserId().equals(parentUserId) ) throw new AuthorizationException();
    
    if(req.getType() == ProcessPcoketMoneyRejectedReqDto.Type.CANCEL){
      //미입금 내역에 대해 취소 처리이면
      // -> 미입금 내역 삭제해주기
      regularPocketMoneyRejectedRepository.delete(rejected);
    }
    else if(req.getType() == ProcessPcoketMoneyRejectedReqDto.Type.SEND){
      //보내기 처리이면
      // -> 돈 보내주기
      VirtualToVirtualReq virtualToVirtualReq = VirtualToVirtualReq.builder()
          .content("정기용돈")
          .money(rejected.getAmount().longValue())
          .userId(rejected.getChild().getUserId())
          .type(Type.POCKET)
          .build();
      try{
        Long parentVirtualAcdountId =
            virtualAccountService.userAccount(parentUserId);
        virtualAccountService.virtualPayment(virtualToVirtualReq, parentVirtualAcdountId);
        regularPocketMoneyRejectedRepository.delete(rejected);  //돈이 제대로 보내졌으면 삭제해주기
      } catch(VirtualAccountException e){
        //실제로 처리되지 못하면
        throw new VirtualAccountException("돈이 부족합니다");
      }
    }
  }
  
  @Override
  public void deleteRegularPocketMoneyByParentUserIdAndChildUserId(Long parentUserId, Long childUserId) {
    regularPocketMoneyRepository.deleteAllByParentUserIdAndChildUserId(parentUserId, childUserId);
  }
  
  @Override
  public void deleteRegularPocketMoneyRejectedByParentUserIdAndChildUserId(Long parentUserId, Long childUserId) {
    regularPocketMoneyRejectedRepository.deleteAllByParentUserIdAndChildUserId(parentUserId, childUserId);
  }
}
