package d209.Idontcare.user.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class RefreshReqDto {
  
  @Schema(description = "발급되었던 리프레시 토큰", example = "dkmdi2kfd9832jif893niufe9834")
  @NotBlank
  private String refreshToken;
}
