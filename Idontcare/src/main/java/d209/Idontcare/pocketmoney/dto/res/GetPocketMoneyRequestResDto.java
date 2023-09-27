package d209.Idontcare.pocketmoney.dto.res;

import d209.Idontcare.pocketmoney.entity.PocketMoneyRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
public class GetPocketMoneyRequestResDto {
  @Schema(description = "요청한 ID", example = "1")
  private Long pocketMoneyRequestId;
  
  private InnerUser parent;
  private InnerUser child;

  @Schema(description = "요청한 돈", example = "10000")
  private Integer amount;
  @Schema(description = "현재 상태", example = "REQUEST | CANCEL | ACCEPTED")
  private PocketMoneyRequest.Type type;
  
  @Schema(description = "요청한 날짜", example = "2022.02.02")
  private LocalDateTime createdAt;
  
  @Schema(description = "취소 예정 날짜", example = "2022.02.04")
  private LocalDateTime cancelDate;
  
  @Schema(description = "요청하는 말", example = "나 돈 줘")
  private String content;
  
  public GetPocketMoneyRequestResDto(Tuple tuple){
      this.pocketMoneyRequestId = (Long)tuple.get("pocketMoneyRequestId");
      this.parent = new InnerUser((Long)tuple.get("parentId"), (String)tuple.get("parentName"));
      this.child = new InnerUser((Long)tuple.get("childId"), (String)tuple.get("childName"));
      this.amount = (Integer)tuple.get("amount");
      this.type = (PocketMoneyRequest.Type)tuple.get("type");
      this.createdAt = (LocalDateTime)tuple.get("createdAt");
      int cancelDate = (int)tuple.get("cancelDate");
      this.cancelDate = LocalDateTime.of(2000 + cancelDate / 10_000,cancelDate % 10_000 / 100, cancelDate % 100, 23, 59);
      this.content = (String)tuple.get("content");
  }
  
  @Getter
  public static class GetPocketMoneyRequestResDtoResult{
    private List<GetPocketMoneyRequestResDto> list = new LinkedList<>();
    
    public GetPocketMoneyRequestResDtoResult(List<GetPocketMoneyRequestResDto> result){
      this.list = result;
    }
  }
}

@Getter
class InnerUser{
  @Schema(description = "유저 ID", example = "1")
  private Long id;
  @Schema(description = "유저 이름", example = "이제성")
  private String name;
  
  public InnerUser(Long id, String name){
    this.id = id;
    this.name = name;
  }
}
