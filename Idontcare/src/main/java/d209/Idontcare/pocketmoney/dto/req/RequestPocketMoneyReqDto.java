package d209.Idontcare.pocketmoney.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class RequestPocketMoneyReqDto {
  @Schema(description = "부모ID", example = "1")
  @NotNull(message = "부모ID가 입력되어야합니다")
  private Long parentUserId;
  
  @Schema(description = "얼마", example = "1000")
  @NotNull(message = "용돈이 입력되어야합니다")
//  @Positive(message = "용돈은 0보다 커야합니다")
  private Integer amount;
  
  @Schema(description = "아이가 부모에게 하고싶은 말", example = "돈 주세요")
  @NotNull(message = "하고싶은 말 항목이 필요합니다")
  private String content;
}
