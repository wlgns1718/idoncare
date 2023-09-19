package d209.Idontcare.user.entity;


import d209.Idontcare.user.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter @Setter
@Table(name="USER")
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

    @Column(name = "REFRESH_TOKEN", length = 500)
    private String refreshToken;

    @Column(name = "NICK_NAME", length = 30)
    private String nickName;



    public  static User toEntity(UserDto userDto){
        return User.builder()
                .userId(userDto.getUserId())
                .phoneNumber(userDto.getPhoneNumber())
                .name(userDto.getName())
                .nickName(userDto.getNickName())
                .role(userDto.getRole())
                .build();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", refreshToken='" + refreshToken + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
