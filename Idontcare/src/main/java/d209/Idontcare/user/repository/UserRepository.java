package d209.Idontcare.user.repository;


import d209.Idontcare.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserId(Long userId);
    
    Optional<User> findByPhoneNumber(String phoneNumber);
  
    Optional<User> findByKakaoId(Long kakaoId);
}
