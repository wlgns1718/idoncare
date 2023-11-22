package KFTC.openBank.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "FIN_TECH_SERVICE")
public class FinTechService {

    @Id
    @Column(name = "FIN_TECH_SERVICE_ID")
    String id;

    @Column(length = 20)
    String name;

    @Column(length = 20)
    String loginId;

    @Column(length = 50)
    String loginPassword;

    @Column(length = 50)
    String clientId;

    @Column(length = 50)
    String clientSecret;

    @Column(name = "REDIRECT_URL")
    String redirectUrl;
    
    public FinTechService(String id,
                          String name,
                          String loginId,
                          String loginPassword,
                          String clientId,
                          String clientSecret,
                          String redirectUrl){
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
    
    }
}
