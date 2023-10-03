package d209.Idontcare.account.dto.req;

import d209.Idontcare.account.dto.res.MonthHistoryRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveRes {

    Long thisMonthExpend; //이번 달 지출 내역
    List<MonthHistoryRes> list; //월별 지출 및 수입 내역
    Long expendThenLast; //지난 달 보다 얼마 더 썼는지
    Long pocketEarn; //이번 달 용돈으로 얼마받았는지
    Long missionEarn; //이번 달 미션으로 얼마 받았는지

    public ActiveRes(Long thisMonthExpend, Long expendThenLast, Long pocketEarn, Long missionEarn) {
        this.thisMonthExpend = thisMonthExpend;
        this.expendThenLast = expendThenLast;
        this.pocketEarn = pocketEarn;
        this.missionEarn = missionEarn;
        this.list = new ArrayList<>();
    }
}
