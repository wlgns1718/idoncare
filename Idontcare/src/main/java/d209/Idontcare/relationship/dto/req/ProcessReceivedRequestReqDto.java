package d209.Idontcare.relationship.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProcessReceivedRequestReqDto {

  @Schema(description = "요청받은 id", example = "1")
  @NotNull
  private Long relationRequestId;
  
  @Schema(description = "수락 or 거절", example = "ACCEPT | REJECT")
  private Type process;
  
  public enum Type{
    ACCEPT, REJECT;
  }
}
