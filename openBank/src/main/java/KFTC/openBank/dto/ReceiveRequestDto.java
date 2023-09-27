package KFTC.openBank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceiveRequestDto {

    /*
    bank_code_std : 입금은행.표준코드
    account_num : 계좌번호
    */

    @Schema(description = "입금은행, 표준코드", example = "입금 은행 표준 코드")
    private String bankCodeStd;
    @Schema(description = "계좌번호", example = "111111111111")
    private String accountNum;



}
