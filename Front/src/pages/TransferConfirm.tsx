import Header from "../components/common/Header";
import TransferMsg from "./../components/wallet/TransferMsg";
import FullBtn from "./../components/common/FullBtn";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/selectors";
import { transferData } from "../store/wallet/atoms";
import { baseUrl } from "../apis/url/baseUrl";
import AxiosHeader from "../apis/axios/AxiosHeader";

function TransferConfirm() {
  const transferAccountData = useRecoilValue(transferData);
  const token = useRecoilValue(userToken);

  const transferMoney = () => {
    axios
      .post(
        baseUrl + `api/account/pay`,
        {
          name: transferAccountData?.account?.clientName,
          bankCode: transferAccountData?.account?.bankCodeStd,
          accountNum: transferAccountData?.account?.accountNum,
          printContent: transferAccountData?.account?.clientName + "에게 입금",
          tranAmt: transferAccountData.amount,
          type: "TRANSFER",
        },
        AxiosHeader({ token })
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
