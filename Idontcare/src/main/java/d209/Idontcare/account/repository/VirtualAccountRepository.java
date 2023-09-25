package d209.Idontcare.account.repository;

import d209.Idontcare.account.entity.VirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VirtualAccountRepository extends JpaRepository<VirtualAccount, Long> {
    
    //유저 정보로 계좌 잔액 찾기
    @Query("SELECT a.balance FROM VirtualAccount a WHERE a.user.userId = :userId")
    Long findBalance(@Param("userId") Long userId);

    //유저 정보로 가상 계좌 찾기
    @Query("SELECT a.virtualAccountId FROM VirtualAccount a WHERE a.user.userId = :userId")
    Long findUser(@Param("userId") Long userId);

    //유저 정보로 가상 계좌 찾기
    @Query("SELECT a FROM VirtualAccount a WHERE a.user.userId = :userId")
    Optional<VirtualAccount> findByUserId(@Param("userId") Long userId);
}
