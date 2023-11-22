package KFTC.openBank.repository;

import KFTC.openBank.domain.Bank;
import KFTC.openBank.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE u.name = :name AND u.phoneNumber =:phoneNumber")
    Optional<User> findUser(@Param("name") String name, @Param("phoneNumber") String phoneNumber);

}
