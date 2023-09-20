import React from "react";
import BankItem, { BankDataType } from "./BankItem";

interface BankListInterface {
  setCurrentSelect: React.Dispatch<React.SetStateAction<string>>;
  banks: BankDataType[];
  onChange: React.Dispatch<React.SetStateAction<string>>;
}

const BankGridList: React.FC<BankListInterface> = ({
  banks,
  onChange,
}) => {
  return (
    <div>
      <div className="text-m text-center my-10">은행선택</div>
      <div className="grid grid-cols-3 gap-4">
        {banks.map((bank, index) => {
          return <BankItem key={index} item={bank} onClick={onChange} />;
        })}
      </div>
    </div>
  );
};
export default BankGridList;
