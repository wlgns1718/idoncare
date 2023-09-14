package KFTC.openBank.repository;

import KFTC.openBank.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


    //계좌번호로 잔액 찾기
    @Query("SELECT a.money FROM BankAccount a WHERE a.id = :accountNumber")
    Long findMoneyById(@Param("accountNumber") Long accountNumer);

    //계좌번호로 사람 실명 찾기
    @Query("SELECT a.name FROM BankAccount a WHERE a.id = :accountNumber and a.bank.id = :bankId and a.birth = :birth")
    String findNameById(@Param("accountNumber")String id, @Param("bankId")String bankId, @Param("birth")String birth);

    //은행 코드와 계좌 번호로 입금자 찾기
    @Query("SELECT a.name FROM BankAccount a WHERE a.id = :accountNumber AND a.bank.id = :bankId")
    String findNameByIdAndBankId(@Param("accountNumber") String accountNumer, @Param("bankId") String bankId);



//    //계좌 번호와 은행 코드로 A에서 B로 돈 출금
//    @Query("SELECT a.bank.id, a.accountNumber FROM Account a WHERE a.id = :accountId")
//    Tuple findBankAndAccountNumberById(@Param("accountId") String accountId);
}
