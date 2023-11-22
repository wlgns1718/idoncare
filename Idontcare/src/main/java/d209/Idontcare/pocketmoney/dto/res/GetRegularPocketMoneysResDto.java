package d209.Idontcare.pocketmoney.dto.res;

import d209.Idontcare.pocketmoney.entity.RegularPocketMoney.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class GetRegularPocketMoneysResDto {
  @Schema(description = "정기용돈 ID", example = "1")
  private Long regularPocketMoneyId;
  
  @Schema(description = "아이 또는 부모 ID", example = "1")
  private Long userId;
  
  @Schema(description = "아이 또는 부모 이름", example = "1")
  private String name;
  
  @Schema(description = "정기용돈 타입", example = "DAY | WEEK | MONTH")
  private Type type;
  
  @Schema(description = "정기용돈 주는 주기", example = "1")
  private Integer cycle;
  
  @Schema(description = "얼마", example = "10000")
  private Integer amount;
  
  @Schema(description = "정기용돈 등록된 날짜", example = "2023.01.01")
  private LocalDateTime createdAt;
  
  public GetRegularPocketMoneysResDto(Tuple tuple){
    this.regularPocketMoneyId = (Long)tuple.get("regularPocketMoneyId");
    this.userId = (Long)tuple.get("userId");
    this.name = (String)tuple.get("name");
    this.type = (Type)tuple.get("type");
    this.cycle = (Integer)tuple.get("cycle");
    this.amount = (Integer)tuple.get("amount");
    this.createdAt = (LocalDateTime)tuple.get("createdAt");
  }
  
  @Getter
  public class Result{
    List<GetRegularPocketMoneysResDto> regularPocketMoneyList = new ArrayList<>();
    public Result(List<GetRegularPocketMoneysResDto> lists){
      regularPocketMoneyList = lists;
    }
  }
}
