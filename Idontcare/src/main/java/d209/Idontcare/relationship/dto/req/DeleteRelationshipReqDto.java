package d209.Idontcare.relationship.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteRelationshipReqDto {
  @Schema(description = "삭제할 관계 ID", example = "1")
  @NotNull
  private Long relationshipId;
}
