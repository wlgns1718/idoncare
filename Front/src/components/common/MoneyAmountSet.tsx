import React, { useState } from "react";
import Number from "../pocketmoney/Number";

interface AmountItem {
  label: string;
  value: number;
}

const amounts: AmountItem[] = [
  { value: 500, label: "5백" },
  { value: 1000, label: "1천" },
  { value: 10000, label: "1만" },
];

const MoneyAmountSet: React.FC = () => {
  const [moneyAmount, setMoneyAmount] = useState(0);

  const handleNumberClick = (num: number | string) => {
    if (typeof num === "number") {
      setMoneyAmount((prevValue) => prevValue * 10 + num);
    }
  };

  const handleDoubleZero = () => {
    setMoneyAmount((prevState) => prevState * 100);
  };

  const handleBackspace = () => {
    setMoneyAmount((prevState) => Math.floor(prevState / 10));
  };

  return (
    <div>
      <div className="text-l text-main font-strong mb-5">
        {moneyAmount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")}{" "}
        원
      </div>

      <div className="mt-auto text-center">
        <div className="flex justify-center gap-10">
          {amounts.map((item) => (
            <div
              key={item.label}
              onClick={() =>
                setMoneyAmount((prevValue) => prevValue + item.value)
              }
              className="px-5 py-3 bg-gray rounded-3xl"
            >
              +{item.label}
            </div>
          ))}
        </div>

        <Number
          bottomLeftText="00"
          bottomRightText="<-"
          onNumberClick={handleNumberClick}
          onLeft={handleDoubleZero}
          onRight={handleBackspace}
        />
      </div>
    </div>
  );
};

export default MoneyAmountSet;
