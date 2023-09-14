package KFTC.openBank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WithdrawReponseDto {

    /*
    rsp_code : 응답코드(API)
     */

    private String rsp_code;

    public WithdrawReponseDto(String rsp_code) {
        this.rsp_code = rsp_code;
    }
}
