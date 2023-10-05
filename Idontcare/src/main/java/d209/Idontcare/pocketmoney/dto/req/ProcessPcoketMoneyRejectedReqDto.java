package d209.Idontcare.pocketmoney.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProcessPcoketMoneyRejectedReqDto {

  @Schema(description = "미지급된 정기용돈 ID", example = "1")
  @NotNull
  private Long regularPocketMoneyRejectedId;
  
  @Schema(description = "처리", example = "CANCEL | SEND", allowableValues = {"CANCEL", "SEND"})
  private Type type;
  
  public enum Type{
    CANCEL, SEND;
  }
}
