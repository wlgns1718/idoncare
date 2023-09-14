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
    Long id;

    @Column(length = 20)
    String name;

    @Column(length = 20)
    String LoginId;

    @Column(length = 50)
    String LoginPassword;

    @Column(length = 50)
    String clientId;

    @Column(length = 50)
    String clientSecret;

    @Column(name = "REDIRECT_URL")
    String redirectUrl;
}
