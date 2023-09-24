package KFTC.openBank.dto;

import KFTC.openBank.domain.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseDto {

    /*
    phoneNumber : 번호
    birth : 생일
    mobileSort : 통신사
    name : 이름
    */

    @Schema(description = "휴대폰 번호", example = "01012345678")
    private String phoneNumber;
    @Schema(description = "생일", example = "1990101")
    private String birth;
    @Schema(description = "이름", example = "김엄마")
    private String name;
}
