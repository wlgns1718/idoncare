package d209.Idontcare.user.entity;


import d209.Idontcare.common.entity.BaseEntity;
import d209.Idontcare.user.dto.UserDetailDto;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDetail extends BaseEntity {

    @Id
    @Column(name = "USER_DETAIL_ID")
    private Long userDetailId;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "BIRTH", length = 20)
    private String birth;

    @Column(name = "EMAIL", length = 30)
    private String email;

    @Column(name = "UPDATED_AT", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public UserDetail toEntity(UserDetailDto userDetailDto,User user){
        return UserDetail.builder()
                .birth(userDetailDto.getBirth())
                .email(userDetailDto.getEmail())
                .user(user)
                .build();
    }
    
    public UserDetail(Long userDetailId, User user, String birth, String email){
        this.userDetailId = userDetailId;
        this.user = user;
        this.birth = birth;
        this.email = email;
    }
}
