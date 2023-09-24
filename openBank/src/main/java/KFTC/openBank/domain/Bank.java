package KFTC.openBank.domain;
import lombok.AllArgsConstructor;
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

    public Bank(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Bank(String id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
    }
}
