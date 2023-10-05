package d209.Idontcare.pocketmoney.entity;

import d209.Idontcare.common.entity.BaseEntity;
import d209.Idontcare.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data @Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor @AllArgsConstructor
public class RegularPocketMoney extends BaseEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long regularPocketMoneyId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private User parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private User child;
  
  @Enumerated(EnumType.STRING)
  private Type type;
  
  private Integer cycle;
  
  private Integer amount;
  
  private Integer dueDate;
  
  @OneToMany(mappedBy = "regularPocketMoney",
      cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<RegularPocketMoneyRejected> rejectedList = new LinkedList<>();
  
  public static enum Type{
    DAY, WEEK, MONTH;
  }
}
