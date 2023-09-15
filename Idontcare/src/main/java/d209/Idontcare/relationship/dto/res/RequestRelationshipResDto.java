package d209.Idontcare.relationship.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data @Builder
public class RequestRelationshipResDto {
  @Schema(description="등록된 관계 요청의 id", example="1")
  private Long requestRelationshipId;
}
