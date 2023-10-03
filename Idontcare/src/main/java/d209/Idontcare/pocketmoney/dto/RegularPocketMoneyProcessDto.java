package d209.Idontcare.pocketmoney.dto;

import lombok.Data;

import javax.persistence.Tuple;

@Data
public class RegularPocketMoneyProcessDto {

  private Long parentUserId;
  private Long childUserId;
  private Integer amount;
  
  public RegularPocketMoneyProcessDto(Tuple t){
    this.parentUserId = (Long)t.get("parentUserId");
    this.childUserId = (Long)t.get("childUserId");
    this.amount = (Integer)t.get("amount");
    
  }
}
