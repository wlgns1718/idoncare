package d209.Idontcare.account.repository;

import d209.Idontcare.account.entity.CashFlow;
import d209.Idontcare.account.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    
    @Query("SELECT a FROM TransactionHistory a WHERE a.user.userId = :userId " +
            "AND YEAR(a.localDateTime) = :year AND MONTH(a.localDateTime) = :month")
    List<TransactionHistory> findTransactionHistory(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT a FROM TransactionHistory a WHERE a.user.userId = :userId AND a.content LIKE %:content% ")
    List<TransactionHistory> findTransactionHistoryByContent(@Param("userId") Long userId, @Param("content") String content);

    //이번 달 지출
    @Query("SELECT SUM(a.amount) FROM TransactionHistory a WHERE a.user.userId = :userId " +
            "AND YEAR(a.localDateTime) = :year AND MONTH(a.localDateTime) = :month AND a.cashFlow = 'WITHDRAWAL'")
    Optional<Long> thisMonthExpend(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    //이번 달 수입
    @Query("SELECT SUM(a.amount) FROM TransactionHistory a WHERE a.user.userId = :userId " +
            "AND YEAR(a.localDateTime) = :year AND MONTH(a.localDateTime) = :month AND a.cashFlow = 'DEPOSIT'")
    Optional<Long> thisMonthEarn(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    //이번 달 받은 용돈
    @Query("SELECT SUM(a.amount) FROM TransactionHistory a WHERE a.user.userId = :userId " +
            "AND YEAR(a.localDateTime) = :year AND MONTH(a.localDateTime) = :month AND a.type = 'MISSION' AND a.cashFlow = 'DEPOSIT'")
    Optional<Long> thisMonthPocket(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);


    //이번 달 받은 미션금
    @Query("SELECT SUM(a.amount) FROM TransactionHistory a WHERE a.user.userId = :userId " +
            "AND YEAR(a.localDateTime) = :year AND MONTH(a.localDateTime) = :month AND a.type = 'POCKET' AND a.cashFlow = 'DEPOSIT'")
    Optional<Long> thisMonthMission(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    //일별 해당 유저의 돈 내역
    @Query("SELECT SUM(t.amount) as amount, DATE(t.localDateTime) as day" +
        " FROM TransactionHistory  t" +
        " WHERE t.user.userId=:userId AND t.cashFlow = :cashFlow" +
        "   AND DATE(t.localDateTime) >= DATE(:before) AND DATE(t.localDateTime) <= DATE(:after) " +
        " GROUP BY DATE(t.localDateTime)")
    List<Tuple> aggregationGroupByDay(@Param("userId")Long userId, @Param("before")LocalDateTime before, @Param("after")LocalDateTime after, @Param("cashFlow")CashFlow cashFlow);
}
