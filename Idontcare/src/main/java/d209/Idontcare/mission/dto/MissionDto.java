package d209.Idontcare.mission.dto;


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

    private Long parentId;

    private Long[] childId;

    private String title;

    private Long amount;

    private Type type;

    private String beforeMessage;

    private String afterMessage;

    @Override
    public String toString() {
        return "MissionDto{" +
                "parentId=" + parentId +
                ", childId=" + Arrays.toString(childId) +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", beforeMessage='" + beforeMessage + '\'' +
                ", afterMessage='" + afterMessage + '\'' +
                '}';
    }
}
