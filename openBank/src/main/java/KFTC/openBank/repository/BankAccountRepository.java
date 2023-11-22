package KFTC.openBank.repository;

import KFTC.openBank.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {


    //계좌번호로 잔액 찾기
    @Query("SELECT a.money FROM BankAccount a WHERE a.id = :accountNumber AND a.bank.id = :bankId")
    Long findMoneyByIdAndBankId(@Param("accountNumber") String accountNumer, @Param("bankId") String bankId);

    //계좌번호로 사람 실명 찾기
    @Query("SELECT a.name FROM BankAccount a WHERE a.id = :accountNumber and a.bank.id = :bankId and a.birth = :birth")
    String findNameById(@Param("accountNumber")String id, @Param("bankId")String bankId, @Param("birth")String birth);

    //계좌 번호, 은행 코드로 입금자 찾기
    @Query("SELECT a.name FROM BankAccount a WHERE a.id = :accountNumber AND a.bank.id = :bankId")
    Optional<String> findNameByIdAndBankId(@Param("accountNumber") String accountNumer, @Param("bankId") String bankId);

    //이름, 은행 코드와 계좌 번호로 실계좌 찾기.
    @Query("SELECT a FROM BankAccount a WHERE a.id = :accountNumber AND a.bank.id = :bankId AND a.name = :name")
    BankAccount findByNameAndBankIdAndId(@Param("name") String name, @Param("bankId") String bankId, @Param("accountNumber") String accountNumber);

}
