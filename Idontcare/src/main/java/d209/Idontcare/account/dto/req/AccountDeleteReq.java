package d209.Idontcare.account.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDeleteReq {

    @Schema(description = "은행 코드", example = "41")
    private String bankCode;
    @Schema(description = "실제 계좌 번호", example = "11111111")
    private String realAccountId;
}
