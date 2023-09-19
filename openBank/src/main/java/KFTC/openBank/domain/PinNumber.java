package KFTC.openBank.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "PIN_NUMBER")
public class PinNumber {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PIN_NUMBER_ID")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    Account account;

    @Column(name = "PIN_CODE")
    Long pinCode;

}
