package KFTC.openBank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositResponseDto {

    /*
    rsp_code : 응답코드(API)
     */
    @Schema(description = "응답번호", example = "A0000")
    private String rsp_code;

    public DepositResponseDto(String rsp_code) {
        this.rsp_code = rsp_code;
    }
}
