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
@NoArgsConstructor @AllArgsConstructor
public class VirtualAccount {

    @Id @Column(name = "VIRTUAL_ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long virtualAccountId;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @Column(name = "BALANCE")
    Long balance;

    @Column(name = "PASSWORD")
    String password;
    
    public VirtualAccount(User user, Long balance, String password){
        this.user = user;
        this.balance = balance;
        this.password = password;
    }
}
