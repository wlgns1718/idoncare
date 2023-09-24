package d209.Idontcare.account.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualToVirtualReq {

    @Schema(description = "받을 유저의 ID")
    private Long userId;

    @Schema(description = "최종 수취 입금 계좌에 남길 내역")
    private String content;

    @Schema(description = "금액")
    private Long money;
}
