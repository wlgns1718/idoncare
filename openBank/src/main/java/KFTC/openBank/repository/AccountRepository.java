package KFTC.openBank.repository;

import KFTC.openBank.domain.Account;
import KFTC.openBank.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    //핀테크 이용번호로 은행코드와 계좌번호 검색
    @Query("SELECT a.bank.id, a.accountNumber FROM Account a WHERE a.id = :accountId")
    Tuple findBankAndAccountNumberById(@Param("accountId") String accountId);

    @Query("SELECT a FROM Account a WHERE a.bank.id = :bankId AND a.accountNumber = :accountNum")
    Optional<Account> findPinNumber(@Param("bankId") String bankId, @Param("accountNum") String bankAccount);
}
