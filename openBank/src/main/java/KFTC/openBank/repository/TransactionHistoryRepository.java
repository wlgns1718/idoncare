package KFTC.openBank.repository;

import KFTC.openBank.domain.BankAccount;
import KFTC.openBank.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

}
