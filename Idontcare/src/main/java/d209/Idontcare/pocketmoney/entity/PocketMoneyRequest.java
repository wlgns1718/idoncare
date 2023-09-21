package d209.Idontcare.pocketmoney.entity;

import d209.Idontcare.common.entity.BaseEntity;
import d209.Idontcare.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Data @EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PocketMoneyRequest extends BaseEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pocketMoneyRequestId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private User parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private User child;
  
  private Integer amount;
  
  @Enumerated(EnumType.STRING)
  private Type type;
  
  private String content;
  
  private Integer cancelDate;
  
  public static enum Type{
    REQUEST, CANCEL, ACCEPTED, REJECT;
  }
}
