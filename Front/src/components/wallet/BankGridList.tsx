import React from "react";
import { bankData } from "../../store/wallet/atoms";
import { useRecoilValue } from "recoil";
import BankItem from "./BankItem";

interface BankListInterface {}

const BankGridList: React.FC<BankListInterface> = () => {
  const bankList = useRecoilValue(bankData);
  return (
    <div className="mb-20">
      <div className="text-m text-center my-6">은행선택</div>
      <div className="grid grid-cols-3 gap-4">
        {bankList.bankList.map((bank, index) => {
          return <BankItem key={index} item={bank} />;
        })}
      </div>
    </div>
  );
};
export default BankGridList;
