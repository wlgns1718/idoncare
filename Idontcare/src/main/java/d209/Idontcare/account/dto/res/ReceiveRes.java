package d209.Idontcare.account.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveRes {

    /*
    bank_code_std : 은행코드
    bank_std : 은행이름
    account_num : 계좌번호
    client_name : 고객 성명
    */

    @Schema(description = "은행코드", example = "은행 표준 코드, 51")
    private String bankCdoeStd;
    @Schema(description = "은행이름", example = "은행 이름, 신한")
    private String bankStd;
    @Schema(description = "계좌번호", example = "999999999999")
    private String accountNum;
    @Schema(description = "고객 성명", example = "김입금")
    private String clientName;

}
