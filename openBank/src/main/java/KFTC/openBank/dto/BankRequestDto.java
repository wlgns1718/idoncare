package KFTC.openBank.dto;


import KFTC.openBank.domain.Bank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankRequestDto {

    @Schema(description = "은행 id")
    String bankId;
    @Schema(description = "은행 이름")
    String bankName;
    @Schema(description = "은행 이미지 파일 경로")
    String bankImage;

    public static BankRequestDto BankToBankRequestDto(Bank bank) {
        return BankRequestDto.builder()
                .bankId(bank.getId())
                .bankName(bank.getName())
                .bankImage(bank.getFilePath())
                .build();
    }
}
