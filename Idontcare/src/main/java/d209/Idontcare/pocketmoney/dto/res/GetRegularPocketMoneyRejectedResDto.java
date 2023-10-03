package d209.Idontcare.pocketmoney.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Data
public class GetRegularPocketMoneyRejectedResDto {
  @Schema(description = "미입금된 정기용돈ID", example = "1")
  private Long regularPocketMoneyRejectedId;
  @Schema(description = "미입금된 정기용돈 금액", example = "10000")
  private Integer amount;
  @Schema(description = "정기용돈이 미입금된 날짜", example = "231203")
  private Integer dueDate;
  
  public GetRegularPocketMoneyRejectedResDto(Tuple t){
    this.regularPocketMoneyRejectedId = (Long)t.get("regularPocketMoneyRejectedId");
    this.amount = (Integer)t.get("amount");
    this.dueDate = (Integer)t.get("dueDate");
  }
}
