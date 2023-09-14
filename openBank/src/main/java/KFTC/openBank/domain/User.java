package KFTC.openBank.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    Long id;

    @Column(length = 20)
    String name;

    @Column(length = 15)
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 15)
    Role role;

}
