package d209.Idontcare.user.dto.res;

import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserInfoResDto {
  @Schema(description = "유저 ID", example = "123")
  private Long userId;
  
  @Schema(description = "유저 닉네임", example = "제성이")
  private String nickname;
  
  @Schema(description = "유저 이름", example = "이제성")
  private String name;
  
  @Schema(description = "유저 타입", example = "PARENT | CHILD")
  private Role role;
  
  @Schema(description = "휴대폰 번호", example = "01012345678")
  private String phoneNumber;
  
  public UserInfoResDto(User user){
    this.userId = user.getUserId();
    this.nickname = user.getNickName();
    this.name = user.getName();
    this.role = user.getRole();
    this.phoneNumber = user.getPhoneNumber();
  }
}
