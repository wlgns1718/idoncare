import React from "react";
import FullBtn from "../common/FullBtn";
import { BankDataType } from "./BankItem";

function AccountNumberForm({
  bank,
  setCurrentSelect
}: {
  bank : BankDataType;
  setCurrentSelect: React.Dispatch<React.SetStateAction<string>>;
}) {
  return (
    <div>
      <div>
        <div
          onClick={() => setCurrentSelect("bank")}
          className="flex p-6 items-center"
        >
          <img src={bank.icon} className="w-[10vw] h-[10vw]"/>
          <div className="text-l">{bank.name}</div>
        </div>
        <input
          type="number"
          className="bg-gray rounded-xl p-4 w-full text-m mb-[60vh]"
          placeholder="'-'없이 계좌번호 입력"
        />
      </div>
      <div>
        <FullBtn buttonText="다음" buttonLink="agreement" className="" />
      </div>
    </div>
  );
}

export default AccountNumberForm;