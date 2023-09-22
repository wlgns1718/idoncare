package d209.Idontcare.mission.dto;

import d209.Idontcare.mission.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMissionInfoDto {

    @Schema(description = "미션 ID", example = "1")
    private Long missionId;

    @Schema(description = "미션 제목", example = "이우철 양치하기")
    private String title;

    @Schema(description = "금액",example = "5000원")
    private Long amount;

    @Schema(description = "타입", example = "REQUEST, PROCESS, UNPAID ,COMPLETE 중에 하나")
    private Type type;

}
