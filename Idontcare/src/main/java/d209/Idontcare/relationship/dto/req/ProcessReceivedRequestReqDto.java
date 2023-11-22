package d209.Idontcare.relationship.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProcessReceivedRequestReqDto {

  @Schema(description = "요청받은 id", example = "1")
  @NotNull(message = "요청받은 id가 입력되어야합니다")
  private Long relationshipRequestId;
  
  @Schema(description = "수락 or 거절", example = "ACCEPT | REJECT")
  @Valid @NotNull(message = "수락 또는 거절 여부가 입력되어야합니다")
  private Type process;
  
  public enum Type{
    ACCEPT, REJECT;
  }
}
