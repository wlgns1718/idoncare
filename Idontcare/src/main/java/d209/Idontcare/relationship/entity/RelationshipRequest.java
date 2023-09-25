package d209.Idontcare.relationship.entity;

import d209.Idontcare.common.entity.BaseEntity;
import d209.Idontcare.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Data @Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor @AllArgsConstructor
public class RelationshipRequest extends BaseEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long relationshipRequestId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private User parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private User child;
  
  public RelationshipRequest(User parent, User child){
    this.parent = parent;
    this.child = child;
  }
}
