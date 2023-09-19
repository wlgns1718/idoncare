package KFTC.openBank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionRequestDto {

    /*
    fintech_use_num : 핀테크이용번호
    bank_tran_id : 은행거래고유번호
    inquiry_type : 조회구분코드(A:All,I:입금, O:출금)
    from_date : 조회시작일자("20160404")
    to_date : 조회종료일자("20160405")
    tran_dtime : 요청일시
     */
    @Schema(description = "핀테크이용번호", example = "0000000001")
    private String fintechUseNum;
    @Schema(description = "은행거래고유번호", example = "null")
    private String bankTranId;
    @Schema(description = "조회구분코드(A:All,I:입금, O:출금) 대소문자 상관 없음", example = "ALL")
    private String inquiryType;
    @Schema(description = "조회시작일자(\"20160404\")", example = "20230915")
    private String fromDate;
    @Schema(description = "조회종료일자(\"20160405\")", example = "20230916")
    private String toDate;
    @Schema(description = "요청일시", example = "null")
    private LocalDateTime TranDtime;

}
