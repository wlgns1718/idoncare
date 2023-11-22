package d209.Idontcare.mission.dto;

import d209.Idontcare.mission.entity.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Tuple;
import java.time.LocalDateTime;


@Getter @Setter
public class MissionSimpleDto {

    private Long missionId;

    private Long parentId;

    private Long childId;

    private String parentName;

    private String childName;

    private String title;

    private Long amount;

    private Type type;

    public MissionSimpleDto(Tuple tuple){
        this.missionId = (Long)tuple.get("missionId");
        this.title = (String)tuple.get("title");
        this.amount = (Long)tuple.get("amount");
        this.type =  (Type) tuple.get("type");
        this.childId = (Long)tuple.get("childId");
        this.parentId = (Long)tuple.get("parentId");
        this.parentName = (String)tuple.get("parentName");
        this.childName = (String)tuple.get("childName");
    }
}
