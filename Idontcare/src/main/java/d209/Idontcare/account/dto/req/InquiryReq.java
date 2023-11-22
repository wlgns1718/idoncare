package d209.Idontcare.account.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class InquiryReq {


    /*
    bank_code_std : 개설기관 표준코드
    account_num : 계좌번호
    bank_tran_id : 은행거래고유번호
    account_holder_info : 예금주 인증정보(주민등록번호 앞 7자리)
    tran_dtime : 요청일시
     */

    @Schema(description = "은행 코드", example = "41")
    private String bankCodeStd;
    @Schema(description = "은행 이름", example = "신한은행")
    private String bankName;
    @Schema(description = "계좌번호", example = "1234567890")
    private String accountNum;
}
