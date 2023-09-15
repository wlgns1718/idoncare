package d209.Idontcare.user.dto;


import d209.Idontcare.user.entity.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long userId;
    private String phoneNumber;
    private String name;
    private Role role;
}
