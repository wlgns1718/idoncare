package d209.Idontcare.relationship.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder @ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestRelationshipReqDto {
  
  @Schema(description="아이의 휴대폰 번호", example="01012345678")
  @NotNull
  private String childPhoneNumber;
}
