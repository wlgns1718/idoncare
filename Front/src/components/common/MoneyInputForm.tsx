import React from "react";
import NumberPlate from "./NumberPlate";
import useComma from './../../hooks/useComma';

type MoneyInputFormProps = {
  text: string;
  balance: number;
};

function MoneyInputForm({ text, balance }: MoneyInputFormProps) {
  const [amount, setAmount] = React.useState(0);

  const PlusChip = [
    {
      value: 500,
      label: "+5백",
    },
    {
      value: 1000,
      label: "+1천",
    },
    {
      value: 5000,
      label: "+5천",
    },
    {
      value: "reset",
      label: "초기화",
    },
  ];

  const handleChipClick = (value: string | number) => {
    if (value == "reset") {
      setAmount(0);
    } else {
      if (typeof value == "number") {
        setAmount(amount + value);
      }
    }
  };
  return (
    <div>
      <div className="m-10 text-center flex-grow">
        <div className="text-l mt-24 mb-20">{text}</div>

        <div className="text-l text-main font-strong mb-5">
          {useComma(amount)} 원
        </div>

        <div className="text-darkgray text-sm mb-10">
          잔액 {useComma(balance)} 원
        </div>
      </div>

      <div className="mt-auto text-center">
        <div className="flex justify-center gap-5  my-10">
          {PlusChip.map((item, index) => {
            return (
              <div
                key={index}
                className="px-5 py-3 bg-gray rounded-3xl"
                onClick={() => {
                  handleChipClick(item.value);
                }}
              >
                {item.label}
              </div>
            );
          })}
        </div>
        <NumberPlate
          bottomLeftText="00"
          value={amount}
          changeNumber={setAmount}
        />
      </div>
    </div>
  );
}

export default MoneyInputForm;
