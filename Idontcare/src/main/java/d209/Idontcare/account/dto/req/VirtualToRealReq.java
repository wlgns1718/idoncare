package d209.Idontcare.account.dto.req;

import d209.Idontcare.account.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class VirtualToRealReq {

    @Schema(description = "최종 수취 고객 성명")
    private String name;

    @Schema(description = "최종 수취 고객 계좌 개설 기관. 표준 코드")
    private String bankCode;

    @Schema(description = "최종 수취 고객 계좌 번호")
    private String accountNum;

    @Schema(description = "최종 수취 입금 계좌에 남길 내역")
    private String printContent;

    @Schema(description = "금액")
    private Long tranAmt;

    @Schema(description = "CHARGE, MISSIONM, POCKET, TRANSFER, RETURN / 충전, 미션, 용돈, 이체, 반환")
    private Type type;

}
