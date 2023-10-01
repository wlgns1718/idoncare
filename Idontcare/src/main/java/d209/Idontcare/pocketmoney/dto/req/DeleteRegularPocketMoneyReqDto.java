package d209.Idontcare.pocketmoney.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeleteRegularPocketMoneyReqDto {

  @Schema(description = "정기용돈 ID", example = "1234512")
  private Long regularPocketMoneyId;
}
