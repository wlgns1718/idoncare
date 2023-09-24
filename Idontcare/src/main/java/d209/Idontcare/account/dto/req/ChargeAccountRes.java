package d209.Idontcare.account.dto.req;

import d209.Idontcare.account.entity.RealAccount;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ChargeAccountRes {

    @Schema(description = "핀번호", example = "123123123123")
    String pinNumber;

    @Schema(description = "충전할 돈", example = "100000")
    Long money;
}
