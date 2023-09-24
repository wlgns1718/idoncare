package d209.Idontcare.account.dto.res;

import d209.Idontcare.account.entity.TransactionHistory;
import d209.Idontcare.account.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionHistoryRes {

    @Schema(description = "거래 이용자", example = "1")
    private Long userId;
    @Schema(description = "거래 내용", example = "스타벅스 결제")
    private String content;
    @Schema(description = "거래 일시", example = "2023-09-22 09:43:25.000000")
    private LocalDateTime localDateTime;
    @Schema(description = "금액", example = "10000")
    private Long amount;
    @Schema(description = "입금 혹은 출금", example = "DEPOSITORY, WITHDRAWAL")
    private Type type;
    @Schema(description = "잔액", example = "100000")
    private Long balance;

    public static TransactionHistoryRes TransactionHistoryToDto(TransactionHistory transactionHistory) {
        TransactionHistoryRes build = new TransactionHistoryRes().builder().
                userId(transactionHistory.getUser().getUserId()).
                content(transactionHistory.getContent()).
                amount(transactionHistory.getAmount()).
                type(transactionHistory.getType()).
                balance(transactionHistory.getBalance()).
                build();
        return build;
    }
}
