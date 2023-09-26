package d209.Idontcare.account.dto.res;

import d209.Idontcare.account.entity.TransactionHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthTransactionHistoryRes {
    /*
    date : 날짜
    historyList : 거래내역
     */
    int date;
    List<TransactionHistoryRes> historyList;

    public MonthTransactionHistoryRes(int date) {
        this.date = date;
        this.historyList = new ArrayList<>();
    }
}
