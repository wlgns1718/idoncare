package d209.Idontcare.account.repository;

import d209.Idontcare.account.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    @Query("SELECT a FROM TransactionHistory a WHERE a.user.userId = :userId " +
            "AND YEAR(a.localDateTime) = :year AND MONTH(a.localDateTime) = :month")
    List<TransactionHistory> findTransactionHistory(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month );

    @Query("SELECT a FROM TransactionHistory a WHERE a.user.userId = :userId AND a.content LIKE %:content% ")
    List<TransactionHistory> findTransactionHistoryByContent(@Param("userId") Long userId, @Param("content") String content);
}
