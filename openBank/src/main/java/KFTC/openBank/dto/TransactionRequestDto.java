package KFTC.openBank.dto;

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

    private String fintechUseNum;
    private String bankTranId;
    private String inquiryType;
    private String fromDate;
    private String toDate;
    private LocalDateTime TranDtime;

}
