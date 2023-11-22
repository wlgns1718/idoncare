package KFTC.openBank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class WithdrawRequestDto {

    /*
    fintech_use_num : 출금계좌핀테크이용번호
    bank_tran_id : 은행거래고유번호
    wd_print_content : 출금 계좌에 남길 내역(내 계좌에 남길 내역)
    cntr_account_num : 입금하고자 하는 계좌 번호
    cntr_account_bank_code_std : 입금하고자 하는 은행 코드
    tran_amt : 금액
    tran_dtime : 요청 일시
    req_client_name : 요청 고객 성명
    recvClientName : 최종 수취 고객 성명
    recvClientBankCode : 최종 수취 고객 계좌 개설 기관. 표준 코드
    recvClientAccountNum : 최종 수취 고객 계좌 번호
    recvDpsPrintContent : 최종 수취 입금 계좌에 남길 내역
     */

    @Schema(description = "출금계좌핀테크이용번호")
    private String fintechUseNum;

    @Schema(description = "은행거래고유번호")
    private String bankTranId;

    @Schema(description = "출금 계좌에 남길 내역(내 계좌에 남길 내역)")
    private String wdPrintContent;

    @Schema(description = "입금하고자 하는 계좌 번호 / 핀테크 계좌 번호")
    private String cntrAccountNum;

    @Schema(description = "입금하고자 하는 은행 코드 / 핀테크 은행 코드")
    private String cntrAccountBankCodeStd;

    @Schema(description = "금액")
    private Long tranAmt;

    @Schema(description = "요청 일시")
    private LocalDateTime tranDtime;

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

}
