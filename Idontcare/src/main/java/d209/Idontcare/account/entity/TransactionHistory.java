package d209.Idontcare.account.entity;

import d209.Idontcare.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory {

    @Id @Column(name = "TRANSACTION_HISTORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long transactionHistoryId;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @Column(name = "CONTENT")
    String content;

    @Column(name="CREATE_AT")
    LocalDateTime localDateTime;

    @Column(name = "AMOUNT")
    Long amount;

    @Column(name = "TYPE") @Enumerated(EnumType.STRING)
    Type type;

    @Column(name = "CASHFLOW") @Enumerated(EnumType.STRING)
    CashFlow cashFlow;

    @Column(name = "BALANCE")
    Long balance;

    public TransactionHistory(User user, LocalDateTime localDateTime, String content, Long amount, Type type, CashFlow cashFlow, Long balance){
        this.user = user;
        this.localDateTime = localDateTime;
        this.content = content;
        this.amount = amount;
        this.type = type;
        this.cashFlow = cashFlow;
        this.balance = balance;
    }
}
