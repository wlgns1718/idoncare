package d209.Idontcare.mission.dto;


import d209.Idontcare.mission.entity.Mission;
import d209.Idontcare.mission.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter @Setter
@Builder
@AllArgsConstructor
public class MissionDto {

    private Long missionId;

    private Long parentId;

    private Long[] childIds;

    private Long childId;

    private String title;

    private Long amount;

    private Type type;

    private String beforeMessage;

    private String afterMessage;


    public static MissionDto toDto(Mission mission){
        return MissionDto.builder()
                .missionId(mission.getMissionId())
                .amount(mission.getAmount())
                .title(mission.getTitle())
                .type(mission.getType())
                .childId(mission.getChild().getUserId())
                .parentId(mission.getParent().getUserId())
                .beforeMessage(mission.getBeforeMessage())
                .afterMessage(mission.getAfterMessage())
                .build();
    }

    @Override
    public String toString() {
        return "MissionDto{" +
                "parentId=" + parentId +
                ", childId=" + Arrays.toString(childIds) +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", beforeMessage='" + beforeMessage + '\'' +
                ", afterMessage='" + afterMessage + '\'' +
                '}';
    }
}
