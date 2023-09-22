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

    @Column(name = "BALANCE")
    Long balance;

}
