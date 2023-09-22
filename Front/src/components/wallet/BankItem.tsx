import React from "react";
import { BankDataType } from "../../types/WalletTypes";
import { sendAccountBank } from "../../store/atoms";
import { useSetRecoilState } from "recoil";

interface BankItemProps {
  item: BankDataType;
  onClick?: (bank : BankDataType) => void;
}

const BankItem: React.FC<BankItemProps> = ({ item }) => {
  const setSelectedBank = useSetRecoilState<BankDataType>(sendAccountBank);
  return (
    <div
      className="border-zinc-500/20 border p-2 rounded-lg shadow-sm flex-col flex items-center"
      onClick={() => setSelectedBank(item)}
    >
      <img
        className="w-[10vw]"
        src={`https://port-0-openbankapi-iciy2almk8xusg.sel5.cloudtype.app/images/삼성카드.png`}
        alt=""
      />
      <span className="text-s">{item?.name}</span>
    </div>
  );
};

export default BankItem;
