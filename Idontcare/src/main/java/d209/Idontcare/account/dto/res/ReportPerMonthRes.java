package d209.Idontcare.account.dto.res;

import d209.Idontcare.account.dto.req.Month;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportPerMonthRes {

    @Schema(description = " 이번 달 지출 내역")
    private Long MonthExpend;

    @Schema(description = " 최근 5개월 별 지출 내역")
    private List<Month> monthList;

    @Schema(description = " 지난 달 대비 얼마 지출")
    private Long MonthExpendThenLast;

    @Schema(description = " 이번 달 받은 용돈")
    private Long MonthPocket;

    @Schema(description = " 이번 달 미션금")
    private Long MonthMission;

}
