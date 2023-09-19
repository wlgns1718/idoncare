package d209.Idontcare.pocketmoney.entity;

import d209.Idontcare.TUser;
import d209.Idontcare.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data @Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor @AllArgsConstructor
public class RegularPocketMoney extends BaseEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long regularPocketMoneyId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private TUser parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private TUser child;
  
  @Enumerated(EnumType.STRING)
  private Type type;
  
  private Integer cycle;
  
  private Integer amount;
  
  private Integer dueDate;
  
  public static enum Type{
    DAY, WEEK, MONTH;
  }
}
