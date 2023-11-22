package d209.Idontcare.user.dto;


import d209.Idontcare.user.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    private Long userId;

    private String phoneNumber;

    private String name;

    private Role role;

    private String refreshToken;

    private String nickName;

}
