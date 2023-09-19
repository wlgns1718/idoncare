package KFTC.openBank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BalanceResponseDto {
        /*
    bank_name  : 은행 이름
    balance_amt  : 계좌 잔액
    */
    @Schema(description = "은행 이름", example = "삼성 카드")
    private String bank_name;
    @Schema(description = "계좌 잔액", example = "1111111111")
    private Long balance_amt;

    public BalanceResponseDto(String bank_name, Long balance_amt) {
        this.bank_name = bank_name;
        this.balance_amt = balance_amt;
    }
}
