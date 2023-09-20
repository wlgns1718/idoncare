import React from "react";
import Header from "../components/common/Header";
import TransferSelectForm from "../components/wallet/TransferSelectForm";

function TransferSelect() {
  return (
    <div>
      <Header pageTitle="보내기 대상" />
      <TransferSelectForm/>
    </div>
  );
}

export default TransferSelect;
