package d209.Idontcare.relationship.entity;

import d209.Idontcare.User;
import d209.Idontcare.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Relationship extends BaseEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long relationshipId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private User parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private User child;
}
