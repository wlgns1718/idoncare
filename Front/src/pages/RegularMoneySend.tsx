import React, { useState } from "react";
import KidSelectForm from "../components/common/KidSelectForm";
import RegularSendForm from "../components/pocketmoney/RegularSendForm";
import RegularSendFormCycle from "../components/pocketmoney/RegularSendFormCycle";
import MoneyPassword from "../components/pocketmoney/MoneyPassword";
import MoneyDone from "../components/pocketmoney/Done";

const SendRegularMoney: React.FC = () => {
  const [step, setStep] = useState(1);
  const [childUserId, setChildUserId] = useState<number | null>(null);
  const [childUserName, setChildUserName] = useState<string | null>(null);
  const [amount, setAmount] = useState<number | null>(null);
  const [cycle, setCycle] = useState<number | null>(null);
  const [type, setType] = useState<string | null>(null);

  const onNextKidSelectForm = (childUserId: number, childUserName: string) => {
    console.log("자녀 선택:", childUserId, childUserName);
    setChildUserId(childUserId);
    setChildUserName(childUserName);
    nextStep();
  };

  const onNextFormAmount = (selectedAmount: number) => {
    setAmount(selectedAmount);
    console.log("금액:", selectedAmount);
    nextStep();
  };

  const onNextFormCycle = (selectedType: string, selectedCycle: number) => {
    setType(selectedType);
    setCycle(selectedCycle);
    console.log("주기:", selectedType, selectedCycle);
    nextStep();
  };

  const nextStep = () => {
    if (step === 4) {
      fetch("http://j9d209.p.ssafy.io:8081/api/pocketmoney/regular", {
        method: "POST",
        headers: {
          Authorization:
          // 부모
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE3MDk5MzM4OTgxNzIwMjAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYyMjEyMTIsImV4cCI6MTY5NjI2NDQxMn0.tdQ_hGCsmNw45LwAJTHGzodkW_BFLiVz9PZc-QUXXjQ",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          type,
          cycle,
          childUserId,
          amount,
        }),
      })
      .then((response) => response.json())
      .then((data) => console.log(data))
      .catch((error) => console.error("Error:", error));
    }

    setStep(step + 1);
};

  console.log("Current step:", step);

  let form;
switch (step) {
case 1:
form = <KidSelectForm onNext={onNextKidSelectForm} />;
break;
case 2:
form = <RegularSendFormCycle onNext={onNextFormCycle} />;
break;
case 3:
form = <RegularSendForm onNext={onNextFormAmount} />;
break;
case 4:
  form = <MoneyPassword onNext={nextStep} />;
  break;
case 5:
form = (
<MoneyDone
title="정기용돈 등록 완료"
content={
<>
<div className="text-m">{childUserName}</div>
<div className="text-m">
매월 {cycle}일 <span className="text-main">{amount}</span>원
</div>
</>
}
ps="충전잔액이 부족하면 이체되지 않습니다."
/>
);
break;

default:
throw new Error("Invalid step");
}

return <div>{form}</div>;
};

export default SendRegularMoney;