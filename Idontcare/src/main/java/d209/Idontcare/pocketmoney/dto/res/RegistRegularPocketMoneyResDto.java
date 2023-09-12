package d209.Idontcare.pocketmoney.dto.res;

import d209.Idontcare.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RegistRegularPocketMoneyResDto{
  
  @Schema(name = "이름", example="이우철")
  String name;
  @Schema(name = "이름", example="이우철")
  String password;
  
  public static class Swagger extends ResponseDto<RegistRegularPocketMoneyResDto> {};
}

