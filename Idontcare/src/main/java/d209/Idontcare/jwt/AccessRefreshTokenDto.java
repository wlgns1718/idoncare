package d209.Idontcare.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AccessRefreshTokenDto {
  @Schema(description = "새로 발급된 액세스 토큰", example = "1hgu213hjg132gh213gh2g3")
  private String accessToken;
  
  @Schema(description = "새로 발급된 리프레시 토큰", example = "217wy27wy72wggy6fgyufhbh")
  private String refreshToken;
}
