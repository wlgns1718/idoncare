package d209.Idontcare.pocketmoney.dto.res;

import d209.Idontcare.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class RegistRegularPocketMoneyResDto{
  
  @Schema(description="등록된 정기용돈의 id", example="1")
  Long regularPocketMoneyId;
}

