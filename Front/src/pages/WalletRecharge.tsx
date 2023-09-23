import Header from "../components/common/Header";
import RechargeInput from "../components/wallet/RechargeInput";
import RechargeAccountList from "../components/wallet/RechargeAccountList";
import FullBtn from "../components/common/FullBtn";

function WalletRecharge() {
  return (
    <div>
      <Header pageTitle="계좌 충전" headerLink="back" headerType="normal" />
      <div>
        <RechargeInput />
        <RechargeAccountList />
      </div>
      <FullBtn buttonText="충전" buttonLink="password" />
    </div>
  );
}

export default WalletRecharge;
