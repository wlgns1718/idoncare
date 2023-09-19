package KFTC.openBank.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BANK")
public class Bank {

    @Id
    @Column(name = "BANK_ID")
    String id;

    @Column
    String name;

    @Column(name = "FILE_PATH")
    String filePath;
}
