package d209.Idontcare.user.entity;


import d209.Idontcare.user.dto.AddressDto;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@NoArgsConstructor
@Setter @Getter
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SIDO")
    private String sido;

    @Column(name = "BNAME")
    private String bName;

    @Column(name = "SIGUNGU")
    private String siGunGu;

    public Address toEntity(AddressDto addressDto,User user){
        return Address.builder()
                .address(addressDto.getAddress())
                .sido(addressDto.getSido())
                .bName(addressDto.getBName())
                .siGunGu(addressDto.getSiGunGu())
                .user(user)
                .build();
    }
}
