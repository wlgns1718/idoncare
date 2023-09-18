package d209.Idontcare;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TUserRepository extends JpaRepository<TUser, Long> {
  Optional<TUser> findByPhoneNumber(String phoneNumber);
}
