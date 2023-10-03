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
public class AccountRegistReq {

    /*
    name : 이름
    bank_code_std : 은행 코드
    account_num : 계좌번호
    tran_dtime : 요청일시
     */

    @Schema(description = "유저의 이름", example = "김부모1")
    private String name;
    @Schema(description = "유저의 휴대폰 번호", example = "01012345678")
    private String phoneNumber;
    @Schema(description = "은행 코드", example = "51")
    private String bankCodeStd;
    @Schema(description = "계좌번호", example = "1234567890")
    private String accountNum;
    @Schema(description = "요청일시", example = "2023-09-14T10:30:00")
    private LocalDateTime tranDtime;
    @Schema(description = "핀테크 서비스 번호", example = "12345678")
    private String finTechServiceId;

}
