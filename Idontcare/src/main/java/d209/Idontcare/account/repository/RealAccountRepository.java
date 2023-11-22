package d209.Idontcare.account.repository;

import d209.Idontcare.account.entity.RealAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RealAccountRepository extends JpaRepository<RealAccount, String> {

    @Query("SELECT a FROM RealAccount a WHERE a.user.userId = :userId")
    Optional<RealAccount> findAccount(@Param("userId") Long userId);
}
