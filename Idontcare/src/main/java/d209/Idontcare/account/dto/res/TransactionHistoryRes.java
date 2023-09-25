package d209.Idontcare.account.dto.res;

import d209.Idontcare.account.entity.CashFlow;
import d209.Idontcare.account.entity.TransactionHistory;
import d209.Idontcare.account.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionHistoryRes {

    @Schema(description = "거래 이용자", example = "1")
    private Long userId;
    @Schema(description = "거래 내용", example = "스타벅스 결제")
    private String content;
    @Schema(description = "거래 일시", example = "2023-09-22")
    private LocalDate localDate;
    @Schema(description = "거래 시간", example = "09:43:25.000000")
    private LocalTime localTime;
    @Schema(description = "거래 요일", example = "1/월, 2/화, 3/수...")
    private int dayOfWeek;
    @Schema(description = "금액", example = "10000")
    private Long amount;
    @Schema(description = "충전, 미션, 용돈, 이체, 반환, 결제", example = "CHARGE, MISSIONM, POCKET, TRANSFER, RETURN, PAYMENT")
    private Type type;
    @Schema(description = "입금, 출금", example = "DEPOSIT, WITHDRAWAL")
    private CashFlow cashFlow;
    @Schema(description = "잔액", example = "100000")
    private Long balance;

    public static TransactionHistoryRes TransactionHistoryToDto(TransactionHistory transactionHistory) {
        TransactionHistoryRes build = new TransactionHistoryRes().builder().
                userId(transactionHistory.getUser().getUserId()).
                content(transactionHistory.getContent()).
                localDate(transactionHistory.getLocalDateTime().toLocalDate()).
                localTime(transactionHistory.getLocalDateTime().toLocalTime()).
                dayOfWeek(transactionHistory.getLocalDateTime().getDayOfWeek().getValue()).
                amount(transactionHistory.getAmount()).
                type(transactionHistory.getType()).
                cashFlow(transactionHistory.getCashFlow()).
                balance(transactionHistory.getBalance()).
                build();
        return build;
    }
}
