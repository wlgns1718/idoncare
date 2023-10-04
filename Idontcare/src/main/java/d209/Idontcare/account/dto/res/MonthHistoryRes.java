package d209.Idontcare.account.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthHistoryRes {
    
    /*
    month : 월
    earn : 수입
    expend : 지출
     */
    
    String day;
    Long earn;
    Long expend;

}
