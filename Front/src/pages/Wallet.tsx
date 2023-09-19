import React from "react";
import { Link } from "react-router-dom";
import Header from "../components/common/Header";

import WalletCard from "../components/wallet/WalletCard";
import TradeHistory from "../components/wallet/TradeHistory";
import BottomNav from "../components/common/BottomNav";

const Wallet = () => {
  return (
    <div>
      <p>Wallet</p>
      <div className="bg-yellow-100">
        <Link to="/">Login</Link>
      </div>
      <Header pageTitle="내 지갑" headerType="normal" headerLink="/home" />
      <div className="m-8">
        <WalletCard />
        <TradeHistory />
      </div>
      <BottomNav />
    </div>
  );
};

export default Wallet;
