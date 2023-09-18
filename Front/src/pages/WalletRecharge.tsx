import React from "react";
import Header from "../components/common/Header";
import RechargeInput from "../components/wallet/RechargeInput";
import RechargeAccountList from "../components/wallet/RechargeAccountList";

function WalletRecharge() {
  return (
    <div>
      <Header pageTitle="계좌 충전" headerLink="/wallet" headerType="normal" />
      <div>
        <RechargeInput />
        <RechargeAccountList />
      </div>
    </div>
  );
}

export default WalletRecharge;
