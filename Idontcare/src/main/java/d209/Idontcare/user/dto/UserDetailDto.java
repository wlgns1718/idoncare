package d209.Idontcare.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class UserDetailDto {

    private Long userId;

    private String birth;

    private String email;

    private AddressDto addressDto;


}
