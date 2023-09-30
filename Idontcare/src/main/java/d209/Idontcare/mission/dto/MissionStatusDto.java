package d209.Idontcare.mission.dto;

import d209.Idontcare.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MissionStatusDto {

    private Long missionId;

    private String afterMessage;

    private String beforeMessage;

    private Long amount;
}
