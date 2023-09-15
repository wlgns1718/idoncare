package d209.Idontcare.relationship.entity;

import d209.Idontcare.User;
import d209.Idontcare.common.entity.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
