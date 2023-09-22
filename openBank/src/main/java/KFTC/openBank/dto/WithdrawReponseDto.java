package KFTC.openBank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WithdrawReponseDto {

    /*
    rsp_code : 응답코드(API)
     */
    @Schema(description = "응답번호")
    private String rsp_code;

    @Schema(description = "요청 고객 성명")
    private String reqClientName;

    @Schema(description = "최종 수취 고객 성명")
    private String recvClientName;

    @Schema(description = "최종 수취 고객 계좌 개설 기관. 표준 코드")
    private String recvClientBankCode;

    @Schema(description = "최종 수취 고객 계좌 번호")
    private String recvClientAccountNum;

    @Schema(description = "최종 수취 입금 계좌에 남길 내역")
    private String recvDpsPrintContent;

    @Schema(description = "금액")
    private Long tran_amt;

    public WithdrawReponseDto(String rsp_code, String reqClientName, String recvClientName, String recvClientBankCode, String recvClientAccountNum, String recvDpsPrintContent, Long tran_amt) {
        this.rsp_code = rsp_code;
        this.reqClientName = reqClientName;
        this.recvClientName = recvClientName;
        this.recvClientBankCode = recvClientBankCode;
        this.recvClientAccountNum = recvClientAccountNum;
        this.recvDpsPrintContent = recvDpsPrintContent;
        this.tran_amt = tran_amt;
    }
}
