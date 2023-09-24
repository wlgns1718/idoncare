package d209.Idontcare.account.dto.res;

import d209.Idontcare.account.entity.RealAccount;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RealAccountRes {

    @Schema(description = "실제 계좌 번호", example = "111111111111")
    String realAccountId;

    @Schema(description = "거래 이용자", example = "1")
    Long userId;

    @Schema(description = "핀번호", example = "123123123123")
    String pinNumber;

    @Schema(description = "은행 이름", example = "신한은행")
    String bankName;

    @Schema(description = "은행 코드", example = "41")
    String bankCode;



    public static RealAccountRes RealAccountToDto(RealAccount account, String decrypAccount, String decrypPinNumber){
        return RealAccountRes.builder()
                .realAccountId(decrypAccount)
                .userId(account.getUser().getUserId())
                .pinNumber(decrypPinNumber)
                .bankName(account.getBankName())
                .bankCode(account.getBankCode())
                .build();
    }
}
