package KFTC.openBank.dto;

import KFTC.openBank.domain.MobileSort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
public class MobileRequestDto {

    private String phoneNumber;

    private String name;

    private String birth;

    private MobileSort mobileSort;
}
