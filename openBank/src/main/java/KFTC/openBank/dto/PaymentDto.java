package KFTC.openBank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {
    /*
    Withdrawer : 출금자
    Depositor : 입금자

    BankId : 은행 id
    BankName : 은행 이름
    Content : 내용
    AccountNum : 계좌 번호

    Money : 돈
     */
    String Withdrawer;
    String WithdrawerBankId;
    String WithdrawerBankName;
    String WithdrawerContent;
    String WithdrawerAccountNum;

    String Depositor;
    String DepositorBankId;
    String DepositorBankName;
    String DepositorContent;
    String DepositorAccountNum;

    Long Money;

    public PaymentDto(String withdrawer, String withdrawerBankId, String withdrawerBankName, String withdrawerContent, String withdrawerAccountNum, String depositor, String depositorBankId, String depositorBankName, String depositorContent, String depositorAccountNum, Long money) {
        Withdrawer = withdrawer;
        WithdrawerBankId = withdrawerBankId;
        WithdrawerBankName = withdrawerBankName;
        WithdrawerContent = withdrawerContent;
        WithdrawerAccountNum = withdrawerAccountNum;
        Depositor = depositor;
        DepositorBankId = depositorBankId;
        DepositorBankName = depositorBankName;
        DepositorContent = depositorContent;
        DepositorAccountNum = depositorAccountNum;
        Money = money;
    }
}
