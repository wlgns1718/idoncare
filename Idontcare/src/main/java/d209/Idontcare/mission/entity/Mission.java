package d209.Idontcare.mission.entity;


import d209.Idontcare.common.entity.BaseEntity;
import d209.Idontcare.mission.dto.MissionDto;
import d209.Idontcare.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Mission extends BaseEntity {

    @Id
    @Column(name = "MISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT")
    private User parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHILD")
    private User child;

    @Column(name = "TITLE", length = 50)
    private String title;

    @Column(name = "AMOUNT")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private Type type;

    @Column(name = "BEFORE_MESSAGE",length = 200)
    private String beforeMessage;

    @Column(name = "AFTER_MESSAGE",length = 200)
    private String afterMessage;

    @Column(name = "COMPLETE_AT")
    private LocalDateTime completeAt;

    public Mission(User parent, User child, String s, long l, Type type, String s1, String o, LocalDateTime o1) {
        this.parent = parent;
        this.child = child;
        this.title = s;
        this.amount = l;
        this.type = type;
        this.beforeMessage = s1;
        this.afterMessage = o;
        this.completeAt = o1;
    }

    public static Mission toRegistEntity(MissionDto missionDto, User child, User parent){
        return Mission.builder()
                .child(child)
                .parent(parent)
                .beforeMessage(missionDto.getBeforeMessage())
                .afterMessage(missionDto.getAfterMessage())
                .amount(missionDto.getAmount())
                .title(missionDto.getTitle())
                .type(missionDto.getType())
                .completeAt(null)
                .build();
    }


}
