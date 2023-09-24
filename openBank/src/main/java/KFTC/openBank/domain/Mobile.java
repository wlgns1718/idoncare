package KFTC.openBank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "MOBILE")
public class Mobile {

    @Id
    @Column(name = "PHONE_NUMBER")
    String id;

    @Column(name = "NAME", length = 10)
    String name;

    @Column(name = "BIRTH", length = 10)
    String birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "MOBILE_SORT", length = 10)
    MobileSort mobileSort;

    public Mobile(String phoneNumber, String name, String birth, MobileSort mobileSort){
        this.id = phoneNumber;
        this.name = name;
        this.birth = birth;
        this.mobileSort = mobileSort;
    }
}
