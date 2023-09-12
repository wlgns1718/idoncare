package d209.Idontcare.pocketmoney.dto.req;

import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @ToString
public class RegistRegularPocketMoneyReqDto {
  @Schema(description="정기용돈타입", example="DAY")
  @Valid private RegularPocketMoney.Type type;
  @NotNull private Long childUserId;
  @NotNull private Integer cycle;
  @NotNull private Integer amount;
}
