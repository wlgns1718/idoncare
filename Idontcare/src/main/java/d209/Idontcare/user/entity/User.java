package d209.Idontcare.user.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@javax.persistence.Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER")
public class User {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PHONE_NUMBER", length = 11)
    private String phoneNumber;

    @Column(name = "NAME", length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private Role role;

}
