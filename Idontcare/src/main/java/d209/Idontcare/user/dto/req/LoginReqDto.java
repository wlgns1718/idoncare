package d209.Idontcare.user.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class LoginReqDto {
  @Schema(description = "카카오 로그인으로 얻은 코드", example = "code")
  private String code;
  @Schema(description = "리다이렉트 할 URL", example = "http://127.0.0.1:5173/login")
  private String redirectUrl;
}