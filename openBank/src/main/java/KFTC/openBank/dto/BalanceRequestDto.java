package KFTC.openBank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BalanceRequestDto {

    /*
    fintech_use_num : 핀테크이용번호
    bank_tran_id : 은행거래고유번호
    tran_dtime : 요청일시
    */

    @Schema(description = "핀테크이용번호", example = "1234567890")
    private String fintechUseName;
    @Schema(description = "은행거래고유번호", example = "하루동안 겹치지 않는 번호")
    private Long bankTranId;
    @Schema(description = "요청일시", example = "2023-09-14T10:30:00")
    private LocalDateTime tranDtime;

}
