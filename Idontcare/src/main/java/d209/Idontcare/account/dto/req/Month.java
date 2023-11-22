package d209.Idontcare.account.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Month {

    @Schema(description = "월", example = "몇 월인지")
    private String name;
    @Schema(description = "수입", example = "MISSIONM, POCKET")
    private String earn;
    @Schema(description = "지출", example = "PAYMENT")
    private String expend;
}
