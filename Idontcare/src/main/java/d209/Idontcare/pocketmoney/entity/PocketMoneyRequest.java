package d209.Idontcare.pocketmoney.entity;

import d209.Idontcare.TUser;
import d209.Idontcare.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PocketMoneyRequest extends BaseEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long regularPocketMoneyId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private TUser parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private TUser child;
  
  private Integer amount;
  
  @Enumerated(EnumType.STRING)
  private Type type;
  
  private String content;
  
  public static enum Type{
    REQUEST, CANCEL, ACCEPTED;
  }
}
