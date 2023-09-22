package d209.Idontcare.account.repository;

import d209.Idontcare.account.entity.RealAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RealAccountRepository extends JpaRepository<RealAccount, Long> {

    @Query("SELECT a.realAccountId FROM RealAccount a WHERE a.user.userId = :userId")
    String findAccount(@Param("userId") Long userId);
}
