package d209.Idontcare.pocketmoney.entity;

import d209.Idontcare.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class RegularPocketMoney {

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
  
  private Integer duDate;
  
  @CreatedDate
  private LocalDateTime createdAt;
  
  public static enum Type{
    DAY, WEEK, MONTH;
  }
}
