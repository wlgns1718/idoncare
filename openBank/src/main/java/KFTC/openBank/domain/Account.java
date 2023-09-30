package KFTC.openBank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "ACCOUNT")
@ToString
public class Account {

    @Id
    @Column(name = "FINTECH_USE_NUM")
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    User user;

    @Column(name = "ACCOUNT_NUMBER")
    String accountNumber;

    @Column(name = "CREATE_AT")
    LocalDate createdAt;

    @Column(name = "UPDATE_AT")
    LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "FIN_TECH_SERVICE_ID")
    FinTechService finTechService;
    
    public Account(String fintechUseNum,
                   Bank bank,
                   User user,
                   String accountNumber,
                   FinTechService finTechService){
        this.id = fintechUseNum;
        this.bank = bank;
        this.user = user;
        this.accountNumber = accountNumber;
        this.finTechService = finTechService;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
}
