package d209.Idontcare.mission.dto;

import d209.Idontcare.mission.entity.Mission;
import d209.Idontcare.mission.entity.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class MissionDetailInfoDto {

    private Long missionId;

    private String parentName;

    private Long childId;

    private Long parentId;

    private String childName;

    private String title;

    private Long amount;

    private String message;

    private Type type;

    private String createdAt;


    public static MissionDetailInfoDto toDto(Mission mission) {
        return MissionDetailInfoDto.builder()
                .missionId(mission.getMissionId())
                .childName(mission.getChild().getNickName())
                .parentName(mission.getParent().getNickName())
                .title(mission.getTitle())
                .amount(mission.getAmount())
                .message(mission.getAfterMessage() != null ? mission.getAfterMessage() : mission.getBeforeMessage())
                .type(mission.getType())
                .createdAt(mission.getCreatedAt().toString().substring(0,10))
                .parentId(mission.getParent().getUserId())
                .childId(mission.getChild().getUserId())
                .build();
    }
}
