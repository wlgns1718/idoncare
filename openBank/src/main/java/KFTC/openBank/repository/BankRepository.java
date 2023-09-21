package KFTC.openBank.repository;

import KFTC.openBank.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, String> {
    
    //은행 코드로 은행 이름 찾기
    @Query("select a.name from Bank a where a.id = :bankId")
    String findNameById(@Param("bankId") String id);

}
