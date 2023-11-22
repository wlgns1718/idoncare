package d209.Idontcare.user.dto.res;

import d209.Idontcare.user.dto.GetUserInfoDto;
import d209.Idontcare.user.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginResDto {
  @Schema(description = "유저 ID", example = "1")
  private String userId;
  
  @Schema(description = "로그인에 대한 메시지", example = "회원정보가 없습니다. 회원가입 페이지로 이동합니다. | 등록된 회원입니다.")
  private String msg;
  
  @Schema(description = "아이돈케어 회원가입 유무", example = "true | false")
  private boolean joined;
  
  @Schema(description = "닉네임", example = "신지훈 | null", nullable = true)
  private String nickname;
  
  @Schema(description = "이메일", example = "xx@xxx.com | null" , nullable = true)
  private String email;
  
  @Schema(description = "유저 타입", example = "PARENT | CHILD" , nullable = true)
  private Role role;
  
  public LoginResDto(GetUserInfoDto dto){
    this.userId = dto.getUserId();
    this.msg = dto.getUserId();
    this.joined = dto.isJoined();
    this.nickname = dto.getNickname();
    this.email = dto.getEmail();
    this.role = dto.getRole();
  }
}
