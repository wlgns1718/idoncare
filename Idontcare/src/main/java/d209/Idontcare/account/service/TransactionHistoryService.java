package d209.Idontcare.account.service;

import d209.Idontcare.account.dto.req.ActiveRes;
import d209.Idontcare.account.dto.res.DailyHistoryRes;
import d209.Idontcare.account.dto.res.MonthHistoryRes;
import d209.Idontcare.account.dto.res.MonthTransactionHistoryRes;
import d209.Idontcare.account.dto.res.TransactionHistoryRes;
import d209.Idontcare.account.entity.CashFlow;
import d209.Idontcare.account.entity.TransactionHistory;
import d209.Idontcare.account.exception.TransactionHistoryException;
import d209.Idontcare.account.exception.UserException;
import d209.Idontcare.account.repository.TransactionHistoryRepository;
import d209.Idontcare.common.exception.AuthorizationException;
import d209.Idontcare.relationship.service.RelationshipService;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import d209.Idontcare.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionHistoryService {

    private final RelationshipService relationshipService;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    //년월별 가상 계좌의 연월별 거래내역 조회
    @Transactional(readOnly = true)
    public  List<MonthTransactionHistoryRes> userTransactionHistoryByDate(Long userId, int year, int month){
        List<MonthTransactionHistoryRes> list = new ArrayList<>();
        for(int i = 0; i < 32; i++){
            list.add(new MonthTransactionHistoryRes(i));
        }
        List<TransactionHistory> transactionHistory = transactionHistoryRepository.findTransactionHistory(userId, year, month);
        for(int i = transactionHistory.size() - 1; i >= 0; i--){
            TransactionHistory trans = transactionHistory.get(i);
            int day = trans.getLocalDateTime().getDayOfMonth();
            list.get(day).getHistoryList().add(TransactionHistoryRes.TransactionHistoryToDto(trans));
        }
        for(int i = 31; i >= 0; i--){
            if(list.get(i).getHistoryList().size() == 0){
                list.remove(i);
            }
        }
        if(list.size() == 0){
            throw new TransactionHistoryException(204, "거래 내역이 없습니다.");
        }
        Collections.sort(list, (a, b) -> b.getDate() - a.getDate());
        return list;
    }
    
    //내용별로 계좌의 거래 내역 조회
    public List<MonthTransactionHistoryRes>  userTransactionHistoryByContent(Long userId, String content){
        List<MonthTransactionHistoryRes> list = new ArrayList<>();
        for(int i = 0; i < 32; i++){
            list.add(new MonthTransactionHistoryRes(i));
        }
        for(TransactionHistory trans : transactionHistoryRepository.findTransactionHistoryByContent(userId, content)){
            int day = trans.getLocalDateTime().getDayOfMonth();
            list.get(day).getHistoryList().add(TransactionHistoryRes.TransactionHistoryToDto(trans));
        }
        for(int i = 31; i >= 0; i--){
            if(list.get(i).getHistoryList().size() == 0){
                list.remove(i);
            }
        }
        if(list.size() == 0){
            throw new TransactionHistoryException(204, "거래 내역이 없습니다.");
        }
        return list;
    }

    //가상 계좌 입출금 시 거래내역 추가
    public void recordTransactionHistory(TransactionHistoryRes trans){
        User user = userRepository.getReferenceById(trans.getUserId());
        TransactionHistory tran = TransactionHistory.builder()
                                    .user(user)
                                    .content(trans.getContent())
                                    .localDateTime(LocalDateTime.now())
                                    .amount(trans.getAmount())
                                    .type(trans.getType())
                                    .cashFlow(trans.getCashFlow())
                                    .balance(trans.getBalance())
                                    .build();
        transactionHistoryRepository.save(tran);
    }

    //최근 5개월의 월별 보고서
    public ActiveRes reportPerMonth(Long userId){
        userRepository.findByUserId(userId).orElseThrow(() -> new UserException.UserNotFoundException(404, "해당 유저는 없습니다."));
        int Month = LocalDateTime.now().getMonthValue();
        Long thisMonthExpend = transactionHistoryRepository.thisMonthExpend(userId, LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue())
            .orElse(0L);
        Long LastMonthExpend = transactionHistoryRepository.thisMonthExpend(userId, LocalDateTime.now().getYear(), LocalDateTime.now().minusMonths(1).getMonth().getValue())
            .orElse(0L);
        Long pocketEarn = transactionHistoryRepository.thisMonthPocket(userId, LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue())
            .orElse(0L);
        Long missionEarn = transactionHistoryRepository.thisMonthMission(userId, LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue())
            .orElse(0L);
        ActiveRes activeReq = new ActiveRes(thisMonthExpend, thisMonthExpend - LastMonthExpend, pocketEarn, missionEarn);
        for(int i = Month-4; i <= Month; i++ ) {
            int year = LocalDateTime.now().getYear();
            int month = i;
            if(i <= 0) {
                year -= 1;
                month += 12;
            }
            Long expend = transactionHistoryRepository.thisMonthExpend(userId, year, month)
                    .orElse(0L);
            Long earn = transactionHistoryRepository.thisMonthEarn(userId, year, month)
                    .orElse(0L);
            activeReq.getList().add(MonthHistoryRes.builder()
                    .day(month + "월")
                    .earn(earn)
                    .expend(expend)
                    .build());
        }
        return activeReq;
    }
    
    public List<DailyHistoryRes> reportPerDailyForParent(Long parentUserId, Long childUserId, LocalDateTime day) {
        if( !relationshipService.relationExistsByParentAndChild(parentUserId, childUserId) ) throw new AuthorizationException();
        
        TreeMap<Integer, DailyHistoryRes> result = new TreeMap<>();
        LocalDateTime prev = day.minusDays(6);
        
        for(int i = 0; i <= 6; i++){
            LocalDateTime curr = day.minusDays(i);
            result.put(curr.getMonthValue() * 100 + curr.getDayOfMonth(), new DailyHistoryRes(curr, 0L, 0L));
        }
        
        List<Tuple> earns = transactionHistoryRepository.aggregationGroupByDay(childUserId, prev, day, CashFlow.DEPOSIT);        //벌었는(earn) 내역
        List<Tuple> expends = transactionHistoryRepository.aggregationGroupByDay(childUserId, prev, day, CashFlow.WITHDRAWAL);  //사용한(expend) 내역

        for(Tuple earn: earns){
            Long amount = (Long)earn.get("amount");
            Date targetDay = (Date)earn.get("day");
            
            DailyHistoryRes targetRes = result.get( (targetDay.getMonth()+1) * 100 + targetDay.getDate());
            targetRes.setEarn(amount);
        }
        
        for(Tuple expend: expends){
            Long amount = (Long)expend.get("amount");
            Date targetDay = (Date)expend.get("day");
            
            DailyHistoryRes targetRes = result.get( (targetDay.getMonth()+1) * 100 + targetDay.getDate());
            targetRes.setExpend(amount);
        }
        
        List<DailyHistoryRes> list = result.values().stream().toList();
        return list;
    }
    
    public List<DailyHistoryRes> reportPerDaily(Long childUserId, LocalDateTime day) {
        TreeMap<Integer, DailyHistoryRes> result = new TreeMap<>();
        LocalDateTime prev = day.minusDays(6);
        
        for(int i = 0; i <= 6; i++){
            LocalDateTime curr = day.minusDays(i);
            result.put(curr.getMonthValue() * 100 + curr.getDayOfMonth(), new DailyHistoryRes(curr, 0L, 0L));
        }
        
        List<Tuple> earns = transactionHistoryRepository.aggregationGroupByDay(childUserId, prev, day, CashFlow.DEPOSIT);        //벌었는(earn) 내역
        List<Tuple> expends = transactionHistoryRepository.aggregationGroupByDay(childUserId, prev, day, CashFlow.WITHDRAWAL);  //사용한(expend) 내역
        
        for(Tuple earn: earns){
            Long amount = (Long)earn.get("amount");
            Date targetDay = (Date)earn.get("day");
            
            DailyHistoryRes targetRes = result.get( (targetDay.getMonth()+1) * 100 + targetDay.getDate());
            targetRes.setEarn(amount);
        }
        
        for(Tuple expend: expends){
            Long amount = (Long)expend.get("amount");
            Date targetDay = (Date)expend.get("day");
            
            DailyHistoryRes targetRes = result.get( (targetDay.getMonth()+1) * 100 + targetDay.getDate());
            targetRes.setExpend(amount);
        }
        
        List<DailyHistoryRes> list = result.values().stream().toList();
        return list;
    }
}
