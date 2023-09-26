package d209.Idontcare.account.repository;

import d209.Idontcare.account.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    @Query("SELECT a FROM TransactionHistory a WHERE a.user.userId = :userId " +
            "AND YEAR(a.localDateTime) = :year AND MONTH(a.localDateTime) = :month")
    List<TransactionHistory> findTransactionHistory(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT a FROM TransactionHistory a WHERE a.user.userId = :userId AND a.content LIKE %:content% ")
    List<TransactionHistory> findTransactionHistoryByContent(@Param("userId") Long userId, @Param("content") String content);

    //이번 달 얼마나 지출 했는지
    @Query("SELECT SUM(a.amount) FROM TransactionHistory a WHERE a.user.userId = :userId " +
            "AND YEAR(a.localDateTime) = :year AND MONTH(a.localDateTime) = :month AND a.type = PAYMENT OR a.type = TRANSFER")
    Optional<Long> ThisMonthExpend(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);
}
