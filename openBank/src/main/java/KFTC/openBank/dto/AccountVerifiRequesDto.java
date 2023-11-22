package KFTC.openBank.dto;


import KFTC.openBank.domain.MobileSort;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AccountVerifiRequesDto {

    /*
    name : 이름
    bank_code_std : 은행 코드
    account_num : 계좌번호
    tran_dtime : 요청일시
     */
    @Schema(description = "이름", example = "김엄마")
    private String name;
    @Schema(description = "은행 코드", example = "51")
    private String bankCodeStd;
    @Schema(description = "계좌번호", example = "1234567890")
    private String accountNum;
    @Schema(description = "요청일시", example = "2023-09-14T10:30:00")
    private LocalDateTime tranDtime;
}
