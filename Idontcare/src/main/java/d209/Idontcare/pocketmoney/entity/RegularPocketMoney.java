package d209.Idontcare.pocketmoney.entity;

import d209.Idontcare.User;
import d209.Idontcare.common.entity.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data @Builder
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
  
  public static enum Type{
    DAY, WEEK, MONTH;
  }
}
