import React from "react";

export type BankDataType = {
  icon: string;
  name: string;
  code?: number;
};

interface BankItemProps {
  item: BankDataType;
  onClick: React.Dispatch<React.SetStateAction<string>>;
}

const BankItem: React.FC<BankItemProps> = ({ item, onClick }) => {
  return (
    <div
      className="border-zinc-500/20 border p-2 rounded-lg shadow-sm flex-col flex items-center"
      onClick={()=>onClick}
    >
      <img
        className="w-[10vw]"
        src={`https://port-0-openbankapi-iciy2almk8xusg.sel5.cloudtype.app/images/삼성카드.png`}
        alt=""
      />
      <span className="text-s">{item.name}</span>
    </div>
  );
};

export default BankItem;
