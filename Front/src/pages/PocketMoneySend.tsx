import React, { useState } from "react";
import PocketMoneySendForm from "../components/pocketmoney/PocketSendForm";
import PocketSendMsgForm from "../components/pocketmoney/PocketSendMsgForm";
import MoneyDone from "../components/pocketmoney/Done";
import MoneyPassword from "../components/pocketmoney/MoneyPassword";
import KidSelectForm from "../components/common/KidSelectForm";

const PocketMoneySend: React.FC = () => {
  const [step, setStep] = useState(1);
  const [childUserId, setChildUserId] = useState<number | null>(null);
  const [childUserName, setChildUserName] = useState<string | null>(null);
  const [amount, setAmount] = useState<number | null>(null);
  const [comment, setComment] = useState<string>("");

  const onNextKidSelectForm = (childUserId: number, childUserName: string) => {
    console.log("자녀 선택:", childUserId, childUserName);
    setChildUserId(childUserId);
    setChildUserName(childUserName);
    nextStep();
  };

  const onNextSendPocketMoneyForm = (selectedAmount: number) => {
    setAmount(selectedAmount);
    console.log("금액:", selectedAmount);
    nextStep();
  };

  const onNextFormMsg = (message: string) => {
    setComment(message);
    console.log("메세지:", message);
    nextStep();
  };  

  const nextStep = () => {
    if (step === 4) {
      fetch("http://j9d209.p.ssafy.io:8081/api/pocketmoney/send", {
        method: "POST",
        headers: {
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc1NjA2MTY0MTgwOTUyMTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU5ODgxMzAsImV4cCI6MTY5NjAzMTMzMH0.D4P1T6o-g_Z0wbvRXtNVCwXNv32tRI4fPdaqQuy3rJM",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          childUserId,
          amount,
          comment
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
      form = <PocketMoneySendForm onNext={onNextSendPocketMoneyForm} />;
      break;
    case 3:
      if (childUserName && amount) {
      form = (
      <PocketSendMsgForm
      kname={childUserName}
      smoney={amount}
      onNext={onNextFormMsg} />
      );
      } else {
        form = <PocketSendMsgForm kname="자녀" smoney={0} onNext={onNextFormMsg} />;
      }
      break;
    case 4:
      form = <MoneyPassword onNext={nextStep} />;
      break;
      case 5:
        form = (
          <MoneyDone
            title="용돈 보내기 완료"
            content={
              <>
                <div className="text-m">{childUserName}</div>
                <div className="text-m text-main">{amount}원</div>
              </>
            }
            ps="남은 잔액:102,000원" // 잔액 처리 로직 추가하기
          />
        );
        break;
      
    default:
      throw new Error("Invalid step");
  }

  return <div>{form}</div>;
};

export default PocketMoneySend;
