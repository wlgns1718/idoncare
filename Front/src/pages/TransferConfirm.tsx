import Header from "../components/common/Header";
import TransferMsg from "./../components/wallet/TransferMsg";
import FullBtn from "./../components/common/FullBtn";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/atoms";
import { transferData } from "../store/wallet/atoms";

function TransferConfirm() {
  const transferAccountData = useRecoilValue(transferData);
  const Token = useRecoilValue(userToken);

  const transferMoney = () => {
    axios
      .post(
        `http://j9d209.p.ssafy.io:8081/api/account/pay`,
        {
          name: transferAccountData?.account?.clientName,
          bankCode: transferAccountData?.account?.bankCodeStd,
          accountNum: transferAccountData?.account?.accountNum,
          printContent: transferAccountData?.account?.clientName + "에게 입금",
          tranAmt: transferAccountData.amount,
          type: "TRANSFER",
        },
        {
          headers: { Authorization: Token as string },
        }
      )
      .then((res) => {
        console.log(res.data);
      });
  };
  return (
    <div>
      <Header pageTitle="보내기" />
      <div className="mx-8">
        <TransferMsg
          name={transferAccountData?.account?.clientName}
          amount={transferAccountData.amount}
        />
        <FullBtn
          buttonText="보내기"
          onClick={() => {
            transferMoney();
          }}
        />
      </div>
    </div>
  );
}

export default TransferConfirm;
