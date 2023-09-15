package d209.Idontcare.relationship.entity;

import d209.Idontcare.TUser;
import d209.Idontcare.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class Relationship extends BaseEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long relationshipId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private TUser parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private TUser child;
}
