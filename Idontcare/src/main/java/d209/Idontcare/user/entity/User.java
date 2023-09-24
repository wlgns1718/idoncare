package d209.Idontcare.user.entity;

import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Table(name="USER")
@Entity
@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "KAKAO_ID")
    private Long kakaoId;

    @Column(name = "PHONE_NUMBER", length = 20, nullable = true)
    private String phoneNumber;

    @Column(name = "NAME", length = 20, nullable = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = true)
    private Role role;

    @Column(name = "NICK_NAME", length = 30, nullable = true)
    private String nickName;
    
    public boolean isParent(){
        return this.role == Role.PARENT;
    }
    public boolean isChild(){
        return this.role == Role.CHILD;
    }
    
    public void setUUID(){
        this.userId = (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE) / 10_000 * 10_000;
    }
    
//    public User(Long userId, Long kakaoId, String phoneNumber, String name, Role role, String nickName){
//        this.userId = userId;
//        this.kakaoId = kakaoId;
//        this.phoneNumber = phoneNumber;
//        this.name = name;
//        this.role = role;
//        this.nickName = nickName;
//    }
}
