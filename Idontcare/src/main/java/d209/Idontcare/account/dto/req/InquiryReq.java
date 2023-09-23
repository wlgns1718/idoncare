package d209.Idontcare.account.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;

public class InquiryReq {


    @Schema(description = "실제 계좌 번호", example = "111111111111")
    String bankCode;

    @Schema(description = "실제 계좌 번호", example = "111111111111")
    String realAccountId;

    @Schema(description = "거래 이용자", example = "1")
    Long userId;
}
