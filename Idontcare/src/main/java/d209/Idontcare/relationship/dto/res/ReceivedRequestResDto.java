package d209.Idontcare.relationship.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Data
public class ReceivedRequestResDto{
  @Schema(description = "요청받은 id", example="1")
  Long relationshipRequestId;
  
  @Schema(description = "요청한 부모", example="요청한 부모의 이름")
  String parentName;
  
  @Schema(description = "요청받은 시간", example="시간형식입니다")
  LocalDateTime createdAt;
  
  public ReceivedRequestResDto(Tuple tuple){
    this.relationshipRequestId = (Long)tuple.get("relationshipRequestId");
    this.parentName = (String)tuple.get("parentName");
    this.createdAt = (LocalDateTime)tuple.get("createdAt");
  }
  
  @Data
  public static class Result{
    @Schema(description = "요청 결과")
    List<ReceivedRequestResDto> requests = new LinkedList<>();
    
    public Result(List<ReceivedRequestResDto> requests){
      this.requests = requests;
    }
  }
}
