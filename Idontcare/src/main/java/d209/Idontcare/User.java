package d209.Idontcare;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class User{
  
  @Id @Column(name="USER_ID")
  private Long userId;
  
  private String phoneNumber;
  
  private String name;
  
  @Enumerated(EnumType.STRING)
  private Type type;
  
  public User setUUID(){
    userId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    return this;
  }
  
  public static enum Type{
    PARENT, CHILD;
  }
}