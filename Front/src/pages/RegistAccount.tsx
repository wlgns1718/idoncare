import { useState } from "react";
import Header from "../components/common/Header";
import KBICON from "../assets/imgs/bank/PNG_KB.png";
import FullBtn from "../components/common/FullBtn";

const banks = [
  {
    icon: KBICON,
    name: "KB국민",
  },
  {
    icon: KBICON,
    name: "IBK기업",
  },
  {
    icon: KBICON,
    name: "NH농협",
  },
  {
    icon: KBICON,
    name: "신한",
  },
  {
    icon: KBICON,
    name: "씨티",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
  {
    icon: KBICON,
    name: "토스",
  },
];

const BankItem = ({
  icon,
  name,
  setCurrentSelect,
}: {
  icon: string;
  name: string;
  setCurrentSelect: React.Dispatch<React.SetStateAction<string>>;
}) => {
  return (
    <div
      className="border-zinc-500/20 border p-2 rounded-lg shadow-sm flex-col flex items-center"
      onClick={() => setCurrentSelect("number")}
    >
      <img className="w-[10vw]" src={icon} alt="" />
      <span className="text-s">{name}</span>
    </div>
  );
};

const InputAccountNumber = ({
  setCurrentSelect,
}: {
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
          <div className="text-l">은행 이름</div>
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
const SelectBank = ({
  setCurrentSelect,
}: {
  setCurrentSelect: React.Dispatch<React.SetStateAction<string>>;
}) => {
  return (
    <div>
      <div className="text-m text-center my-10">은행선택</div>
      <div className="grid grid-cols-3 gap-4">
        {banks.map((bank, index) => {
          return (<BankItem key={index} icon={bank.icon} name={bank.name} setCurrentSelect={setCurrentSelect}/>);
        })}
      </div>
    </div>
  );
};

function RegistAccount() {

  const [currentSelect, setCurrentSelect] = useState("bank");
  return (
    <div>
      <Header pageTitle="충전 계좌 등록" />
      {currentSelect == "bank" ? (
        <SelectBank setCurrentSelect={setCurrentSelect} />
      ) : (
        <InputAccountNumber setCurrentSelect={setCurrentSelect} />
      )}
    </div>
  );
}

export default RegistAccount;
