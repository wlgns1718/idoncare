package d209.Idontcare.relationship.dto.req;

import d209.Idontcare.relationship.entity.RelationshipRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReceivedRequestResDto {
  @Schema(description = "요청 받은 리스트")
  List<Request> requests;
  
  public ReceivedRequestResDto(List<RelationshipRequest> list){
    requests = list.stream().map(Request::new).toList();
  }
  
  @Data
  public class Request{
    @Schema(description = "요청받은 id", example="1")
    Long relationshipRequestId;
    
    @Schema(description = "요청한 부모", example="요청한 부모의 이름")
    String parentName;
    
    @Schema(description = "요청받은 시간", example="시간형식입니다")
    LocalDateTime createdAt;
    
    public Request(RelationshipRequest request){
      this.relationshipRequestId = request.getRelationshipRequestId();
      this.parentName = request.getParent().getName();
      this.createdAt = request.getCreatedAt();
    }
  }
}
