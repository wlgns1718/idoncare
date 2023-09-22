package KFTC.openBank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TransactionResponseDto {

    /*
    rsp_code : 응답코드(API)
    res_list : 조회된 거래내역(object)
            tran_date : 거래일자("2016031")
            tran_time : 거래시간("113000")
            inout_type : 입금 출금 구분
            print_content : 통장 인자 내용
            tran_amt : 거래금액
            after_balance_amt : 거래 후 잔액
     */
    @Schema(description = "응답코드(API)", example = "A0000")
    private String RspCode;
    @Schema(description = "거래내역", example = "JSON")
    private List<ResList> resList = new ArrayList<>();

    public TransactionResponseDto(String rspCode, List<ResList> resList) {
        RspCode = rspCode;
        this.resList = resList;
    }
}
