package d209.Idontcare.relationship.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
public class RelationshipResDto {
  @Schema(description = "관계 ID", example = "1")
  Long relationshipId;
  
  @Schema(description = "유저 ID", example = "2")
  Long userId;
  @Schema(description = "유저의 이름", example = "2415263725")
  String userName;
  
  @Schema(description = "관계 맺은 날짜", example="시간")
  LocalDateTime createdAt;
  
  public RelationshipResDto(Tuple tuple){
    this.relationshipId = (Long)tuple.get("relationshipId");
    this.userId = (Long)tuple.get("userId");
    this.userName = (String)tuple.get("userName");
    this.createdAt = (LocalDateTime)tuple.get("createdAt");
  }
  
  @Getter
  public static class RelationshipResDtoResult{
    @Schema(description = "관계 리스트")
    List<RelationshipResDto> relationList = new LinkedList<>();
    
    public RelationshipResDtoResult(List<RelationshipResDto> relationList){
      this.relationList = relationList;
    }
  }
}
