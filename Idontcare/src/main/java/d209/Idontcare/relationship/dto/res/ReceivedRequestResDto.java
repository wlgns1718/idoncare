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
  private Long relationshipRequestId;
  
  @Schema(description = "요청한 부모", example="요청한 부모의 이름")
  private String parentName;
  
  @Schema(description = "부모의 폰번호", example="요청한 부모의 폰번호")
  private String parentPhoneNumber;
  
  @Schema(description = "요청받은 시간", example="시간형식입니다")
  private LocalDateTime createdAt;
  
  
  public ReceivedRequestResDto(Tuple tuple){
    this.relationshipRequestId = (Long)tuple.get("relationshipRequestId");
    this.parentName = (String)tuple.get("parentName");
    this.parentPhoneNumber = (String)tuple.get("parentPhoneNumber");
    this.createdAt = (LocalDateTime)tuple.get("createdAt");
  }
  
  @Data
  public static class ReceivedRequestResDtoResult{
    @Schema(description = "요청 결과")
    List<ReceivedRequestResDto> requests = new LinkedList<>();
    
    public ReceivedRequestResDtoResult(List<ReceivedRequestResDto> requests){
      this.requests = requests;
    }
  }
}
