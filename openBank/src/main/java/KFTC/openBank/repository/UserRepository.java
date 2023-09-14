package KFTC.openBank.repository;

import KFTC.openBank.domain.Bank;
import KFTC.openBank.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
