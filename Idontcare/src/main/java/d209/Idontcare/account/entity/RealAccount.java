package d209.Idontcare.account.entity;

import d209.Idontcare.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealAccount {

    @Id @Column(name = "READ_ACCOUNT_ID")
    Long realAccountId;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @Column(name = "PIN_NUMBER")
    Long pinNumber;

    @Column(name = "REAL_ACCOUNT_NUMBER")
    String realAccountNumber;
}
