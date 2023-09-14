package KFTC.openBank.repository;

import KFTC.openBank.domain.BankAccount;
import KFTC.openBank.domain.TransactionHistory;
import KFTC.openBank.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    @Query("SELECT a FROM TransactionHistory a WHERE DATE_FORMAT(a.localDateTime, '%Y%m%d') >= :startDate AND DATE_FORMAT(a.localDateTime, '%Y%m%d') " +
            "<= :endDate AND a.bankAccount.id = :accountNumber")
    List<TransactionHistory> findByDateAll(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("accountNumber") String accountNumber);

    @Query("SELECT a FROM TransactionHistory a WHERE DATE_FORMAT(a.localDateTime, '%Y%m%d') >= :startDate AND DATE_FORMAT(a.localDateTime, '%Y%m%d') " +
            "<= :endDate AND a.type = :type AND a.bankAccount.id = :accountNumber")
    List<TransactionHistory> findByCondition(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("accountNumber") String accountNumber, @Param("type") Type type);
}
