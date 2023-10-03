package d209.Idontcare.mission.dto;


import d209.Idontcare.mission.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class GetMissionDetailInfoDto {

    @Schema(description = "미션 ID", example = "1")
    private Long MissionId;

    @Schema(description = "부모 정보", example = "김부모")
    private String ParentName;

    @Schema(description = "자녀 정보", example = "김자식")
    private String ChildName;

    @Schema(description = "미션 제목", example = "내 방 청소하기")
    private String title;

    @Schema(description = "미션금", example = "5000원")
    private Long mount;

    @Schema(description = "메시지", example = "엄마 오늘 청소 열심히 할게요 ㅎㅎ")
    private String message;

    @Schema(description = "상태", example = "REQUEST, PROCESS, UNPAID ,COMPLETE 중에 하나")
    private Type type;

    @Schema(description = "생성일",example = "2023-10-03")
    private LocalDateTime createdAt;

}
