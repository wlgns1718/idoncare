import { useState } from "react"
import Header from "../components/common/Header";
import RechargeInput from "../components/wallet/RechargeInput";
import RechargeAccountList from "../components/wallet/RechargeAccountList";
import FullBtn from "../components/common/FullBtn";
import axios from "axios";
import { userToken } from "../store/common/selectors";
import { useRecoilValue } from "recoil";
import { rechargeAccount } from "../store/wallet/atoms";
import { baseUrl } from "../apis/url/baseUrl";
import AxiosHeader from "../apis/axios/AxiosHeader";
import BottomNav from "../components/common/BottomNav";

function WalletRecharge() {
  const token = useRecoilValue(userToken);
  const myRechargeAccount = useRecoilValue(rechargeAccount);
  const [rechargeAmount, setRechargeAmount] = useState(0);

  const rechageMoney = () => {
    axios
      .post(
        baseUrl + `api/account/charge`,
        {
          pinNumber: myRechargeAccount?.pinNumber,
          money: rechargeAmount,
          type: "CHARGE",
        },
        AxiosHeader({ token })
      )
      .then((res) => {
        console.log(res.data);
      });
  };

  return (
    <div>
      <Header pageTitle="계좌 충전" headerLink="back" headerType="normal" />
      <div className="mx-8">
        <RechargeInput
          rechargeAmount={rechargeAmount}
          setRechargeAmount={setRechargeAmount}
        />
        <RechargeAccountList />
        <FullBtn buttonText="충전" onClick={rechageMoney} />
      </div>
      <BottomNav />
    </div>
  );
}

export default WalletRecharge;
