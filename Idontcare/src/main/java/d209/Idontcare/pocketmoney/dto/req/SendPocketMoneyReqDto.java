package d209.Idontcare.pocketmoney.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class SendPocketMoneyReqDto {
  @Schema(description = "아이ID", example = "1")
  @NotNull(message = "아이ID가 입력되어야합니다")
  private Long childUserId;
  
  @Schema(description = "얼마", example = "1000")
  @NotNull(message = "용돈이 입력되어야합니다") @Positive(message = "용돈은 0보다 커야합니다")
  private Long amount;
  
  @Schema(description = "코멘트", example = "잘 쓰렴")
  @NotEmpty(message = "하고싶은 말을 입력해야 합니다")
  private String comment;
}
