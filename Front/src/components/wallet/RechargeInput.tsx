import React from "react";

function RechargeInput() {
  const [rechargeAmount, setRechargeAmount] = React.useState(0);

  const Amounts = [
    {
      value: 500,
      label: "5백",
    },
    {
      value: 1000,
      label: "1천",
    },
    {
      value: 10000,
      label: "1만",
    },
  ];
  return (
    <div>
      <div className="text-center text-m my-10">
        i don't care 머니로 충전할 금액
      </div>
      <div className="text-l text-main fontFamily-strong text-center my-10">
        {rechargeAmount
          .toString()
          .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")}{" "}
        원
      </div>
      <div className="flex justify-center gap-10  my-10">
        {Amounts.map((item, index) => {
          return (
            <div
              key={index}
              className="px-5 py-3 bg-gray rounded-3xl"
              onClick={() => {
                setRechargeAmount(rechargeAmount + item.value);
              }}
            >
              + {item.label}
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default RechargeInput;
