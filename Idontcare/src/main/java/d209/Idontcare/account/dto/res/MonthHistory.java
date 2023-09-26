package d209.Idontcare.account.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthHistory {
    
    /*
    month : 월
    earn : 수입
    expend : 지출
     */
    
    int month;
    Long earn;
    Long expend;

}
