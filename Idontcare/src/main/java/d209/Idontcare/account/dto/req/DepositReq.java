package d209.Idontcare.account.dto.req;

import d209.Idontcare.account.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositReq {

    @Schema(description = " 핀테크 기업의 계좌 번호")
    private String cntrAccountNum;

    @Schema(description = "핀테크 기업의 은행 코드")
    private String cntrAccountBankCodeStd;

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
    private Long tranAmt;

}
