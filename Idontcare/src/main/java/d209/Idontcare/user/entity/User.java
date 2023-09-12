package d209.Idontcare.user.entity;


import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@javax.persistence.Entity
@Builder
@NoArgsConstructor
@Table(name="USER")
public class User {

    @Id
    Long userId;



}
