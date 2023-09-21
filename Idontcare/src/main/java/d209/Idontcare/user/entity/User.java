package d209.Idontcare.user.entity;


import d209.Idontcare.user.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@Table(name="USER")
@Entity
@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;

    @Column(name = "NAME", length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private Role role;

    @Column(name = "NICK_NAME", length = 30)
    private String nickName;
    
    public boolean isParent(){
        return this.role == Role.PARENT;
    }
    public boolean isChild(){
        return this.role == Role.CHILD;
    }
}
