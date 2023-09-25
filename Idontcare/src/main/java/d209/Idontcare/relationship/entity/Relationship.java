package d209.Idontcare.relationship.entity;

import d209.Idontcare.common.entity.BaseEntity;
import d209.Idontcare.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class Relationship extends BaseEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long relationshipId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private User parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private User child;
  
  public Relationship(User parent, User child){
    this.parent = parent;
    this.child = child;
  }
}
