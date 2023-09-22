import { useState } from "react";
import Header from "../components/common/Header";
import FullBtn from "../components/common/FullBtn";
import BankGridList from "../components/wallet/BankGridList";
import { BankDataType } from "../types/WalletTypes";
import AccountSelectForm from "../components/wallet/AccountSelectForm";

const InputAccountNumber = ({
  selectedBank,
  setCurrentSelect,
}: {
  selectedBank: BankDataType | null;
  setCurrentSelect: React.Dispatch<React.SetStateAction<string>>;
}) => {
  return (
    <div>
      <div>
        <div
          onClick={() => setCurrentSelect("bank")}
          className="flex p-6 items-center"
        >
          <div className="w-[10vw] h-[10vw]">아이콘</div>
          <div className="text-l">{selectedBank?.name}</div>
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
};

function RegistAccount() {
  const [selectedBank, setSelectedBank] = useState<BankDataType | null>(null);
  const [currentSelect, setCurrentSelect] = useState("bank");

  const handleSelectBank = (bank: BankDataType) => {
    setSelectedBank(bank);
    setCurrentSelect("account");
  };

  return (
    <div>
      <Header pageTitle="충전 계좌 등록" />
      <AccountSelectForm />
    </div>
  );
}

export default RegistAccount;
