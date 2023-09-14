package KFTC.openBank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WithdrawRequestDto {

    /*
    fintech_use_num : 출금계좌핀테크이용번호
    bank_tran_id : 은행거래고유번호
    wd_print_content : 출금 계좌에 남길 내역(내 계좌에 남길 내역)
    cntr_account_num : 입금하고자 하는 계좌 번호
    cntr_account_bank_code_std : 입금하고자 하는 은행 코드
    dps_print_content : 입금 계좌에 남길 내역(보낼 계좌에 남길 내역)
    tran_dtime : 요청 일시
    req_client_name : 요청 고객 성명
     */

    private String fintechUseNum;
    private String bankTranId;
    private String wdPrintContent;
    private String cntrAccountNum;
    private String cntrAccountBankCodeStd;
    private String dpsPrintContent;
    private String tranDtime;
    private String reqClientName;

}
