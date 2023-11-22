package d209.Idontcare.account.entity;

import d209.Idontcare.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
public class RealAccount {

    @Id @Column(name = "READ_ACCOUNT_ID", length = 100)
    String realAccountId;

    @OneToOne
    @JoinColumn(name = "userId")
    User user;

    @Column(name = "PIN_NUMBER", length = 100)
    String pinNumber;

    @Column(name = "BANK_NAME")
    String bankName;

    @Column(name = "BANK_CODE")
    String bankCode;

    public RealAccount(String realAccountId, User user,
                       String pinNumber, String bankName,
                       String bankCode){
        this.realAccountId = realAccountId;
        this.user = user;
        this.pinNumber = pinNumber;
        this.bankName = bankName;
        this.bankCode = bankCode;
    }
}
