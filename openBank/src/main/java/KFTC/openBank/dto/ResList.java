package KFTC.openBank.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResList {
    /*
    tran_date : 거래일자("2016031")
    tran_time : 거래시간("113000")
    inout_type : 입금 출금 구분
    print_content : 통장 인자 내용
    tran_amt : 거래금액
    after_balance_amt : 거래 후 잔액
    */
    private String TranData;
    private String TranTime;
    private String inout_type;
    private String print_content;
    private Long tran_amt;
    private Long after_balance_amt;

    public ResList(String tranData, String tranTime, String inout_type, String print_content, Long tran_amt, Long after_balance_amt) {
        TranData = tranData;
        TranTime = tranTime;
        this.inout_type = inout_type;
        this.print_content = print_content;
        this.tran_amt = tran_amt;
        this.after_balance_amt = after_balance_amt;
    }
}
