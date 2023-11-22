package d209.Idontcare.pocketmoney.entity;

import d209.Idontcare.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Data
public class RegularPocketMoneyRejected {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long regularPocketMoneyRejectedId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private RegularPocketMoney regularPocketMoney;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private User parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private User child;
  
  private Integer amount;
  
  private Integer dueDate;
}
