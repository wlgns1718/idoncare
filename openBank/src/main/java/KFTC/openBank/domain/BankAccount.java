package KFTC.openBank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

    @Id
    @Column(name = "ACCOUNT_NUMBER")
    String id;

    @ManyToOne @JoinColumn(name = "BANK_ID")
    Bank bank;

    @Column(name = "MONEY")
    Long money;

    @Column(name = "name")
    String name;

    @Column(name = "birth")
    String birth;

    public Long withdraw(Long money){
        this.money -= money;
        return this.money;
    }

    public Long deposit(Long money){
        this.money += money;
        return this.money;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + id + '\'' +
                ", bank=" + bank +
                ", money=" + money +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}
