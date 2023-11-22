package d209.Idontcare.pocketmoney.dto.req;

import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProcessPocketMoneyRequestReqDto {
  
  @Schema(description = "요청한 ID", example = "1")
  @NotNull
  private Long pocketMoneyRequestId;
  
  @Schema(description="용돈 수락 거절", example="ACCEPT | REJECT", allowableValues = {"ACCEPT", "REJECT"})
  @Valid
  @NotNull(message = "용돈에 대해 수락 또는 거절이 필요합니다")
  private Type type;
  
  public enum Type{
    ACCEPT, REJECT
  }
}
