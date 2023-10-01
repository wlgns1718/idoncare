package d209.Idontcare.account.dto.req;

import d209.Idontcare.account.dto.res.MonthHistoryRes;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class ActiveReq {

    @Schema(description = "자녀의 userId", example = "252315325234")
    Long userId;
}
