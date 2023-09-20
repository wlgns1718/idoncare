package d209.Idontcare.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressDto {
    
    @Schema(description = "전체 주소", example="")
    private String address;

    @Schema(description = "시도", example="나도 모름")
    private String sido;

    @Schema(description = "모름", example="나도 모름")
    private String bName;

    @Schema(description = "시군구", example="나도 모름")
    private String siGunGu;

}
