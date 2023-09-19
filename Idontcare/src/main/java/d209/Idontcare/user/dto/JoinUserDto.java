package d209.Idontcare.user.dto;


import d209.Idontcare.user.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JoinUserDto {

    private Long userId;

    private String phoneNumber;

    private String name;

    private String nickName;

    private String birth;

    private String email;

    private AddressDto addressDto;

    private Role role;


}
