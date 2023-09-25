import Header from "../components/common/Header";
import RechargeInput from "../components/wallet/RechargeInput";
import RechargeAccountList from "../components/wallet/RechargeAccountList";
import FullBtn from "../components/common/FullBtn";
import axios from "axios";
import { userToken } from "../store/common/atoms";
import { useRecoilValue } from "recoil";
import { rechargeAccount } from "../store/wallet/atoms";

function WalletRecharge() {
  const Token = useRecoilValue(userToken);
  const myRechargeAccount = useRecoilValue(rechargeAccount);

  const rechageMoney = () => {
    axios
      .post(
        `http://j9d209.p.ssafy.io:8081/api/account/charge`,
        {
          pinNumber: myRechargeAccount?.pinNumber,
          money: 10000,
          type: "CHAGE",
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
      <Header pageTitle="계좌 충전" headerLink="back" headerType="normal" />
      <div>
        <RechargeInput />
        <RechargeAccountList />
      </div>
      <FullBtn buttonText="충전" buttonLink="password" onClick={rechageMoney} />
    </div>
  );
}

export default WalletRecharge;
