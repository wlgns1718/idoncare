package d209.Idontcare.account.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class InquiryReq {


    /*
    bank_code_std : 개설기관 표준코드
    account_num : 계좌번호
    bank_tran_id : 은행거래고유번호
    account_holder_info : 예금주 인증정보(주민등록번호 앞 7자리)
    tran_dtime : 요청일시
     */

    @Schema(description = "은행 코드", example = "51")
    private String bankCodeStd;
    @Schema(description = "계좌번호", example = "1234567890")
    private String accountNum;
    @Schema(description = "은행거래고유번호", example = "T9916764")
    private String bankTranId;
    @Schema(description = "예금주 인증정보", example = "20000101")
    private String accountHolderInfo;
    @Schema(description = "요청일시", example = "2023-09-14T10:30:00")
    private LocalDateTime tranDtime;
}
