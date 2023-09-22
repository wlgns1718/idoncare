package KFTC.openBank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TRANSACTION_HISTORY")
public class TransactionHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_HISTORY_ID")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_NUMBER")
    BankAccount bankAccount;

    @Column(length = 100)
    String content;

    @Column
    LocalDateTime localDateTime;

    @Column
    Long amount;

    @Enumerated(EnumType.STRING)
    @Column
    Type type;

    @Column
    Long balance;

    public TransactionHistory(BankAccount bankAccount, String content, LocalDateTime localDateTime, Long amount, Type type, Long balance) {
        this.bankAccount = bankAccount;
        this.content = content;
        this.localDateTime = localDateTime;
        this.amount = amount;
        this.type = type;
        this.balance = balance;
    }
}
