package d209.Idontcare.pocketmoney.dto.req;

import d209.Idontcare.pocketmoney.entity.RegularPocketMoney.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter @Builder @ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistRegularPocketMoneyReqDto {
  @Schema(description="정기용돈타입", example="DAY | WEEK | MONTH", allowableValues = {"DAY", "WEEK", "MONTH"})
  @Valid @NotNull(message = "용돈타입이 입력되어야합니다")
  private Type type;
  
  @Schema(description="아이의 userId", example="5521425987")
  @NotNull(message = "자녀ID가 입력되어야합니다")
  private Long childUserId;
  
  @Schema(description="주기", example="1", nullable=true)
  private Integer cycle;
  
  @Schema(description="얼마", example="10000")
  @NotNull(message = "용돈이 입력되어야합니다") @Positive(message = "용돈은 0보다 커야합니다")
  private Integer amount;
}
