package KFTC.openBank.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InquiryResponseDto {
    /*
    account_holder_name : 예금주 성명
     */

    @Schema(description = "예금주 성명", example = "아이돈케어")
    private String accountHolderName;

    public InquiryResponseDto(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
}
