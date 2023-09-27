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
public class VirtualToVirtualReq {

    @Schema(description = "받을 유저의 ID")
    private Long userId;

    @Schema(description = "최종 수취 입금 계좌에 남길 내역")
    private String content;

    @Schema(description = "금액")
    private Long money;

    @Schema(description = "CHARGE, MISSION, POCKET, TRANSFER, RETURN/충전, 미션, 용돈, 이체, 반환")
    private Type type;

}
