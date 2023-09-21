package d209.Idontcare.mission.dto;

import d209.Idontcare.mission.entity.Type;
import lombok.Getter;

import javax.persistence.Tuple;
import java.time.LocalDateTime;


@Getter
public class MissionSimpleDto {

    private Long missionId;

    private String title;

    private Long amount;

    private Type type;

    public MissionSimpleDto(Tuple tuple){
        this.missionId = (Long)tuple.get("missionId");
        this.title = (String)tuple.get("title");
        this.amount = (Long)tuple.get("amount");
        this.type =  (Type) tuple.get("type");
    }
}
