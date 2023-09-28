import { useState } from "react";
import KidFormAmount from "../components/pocketmoney/kid/KidFormAmount";
import KidFormMsg from "../components/pocketmoney/kid/KidFormMsg";
import MoneyDone from "../components/pocketmoney/Done";
import ParentSelectForm from "../components/common/ParentSelectForm";

function KidDemandMoney() {
  const [step, setStep] = useState(1);
  const [parentUserId, setParentUserId] = useState<number | null>(null);
  const [parentUserName, setParentUserName] = useState<string | null>(null);
  const [amount, setAmount] = useState<number | null>(null);
  const [content, setContent] = useState<string>("");

  const onNextParentSelectForm = (
    parentUserId: number,
    parentUserName: string
  ) => {
    console.log("부모 선택:", parentUserId, parentUserName);
    setParentUserId(parentUserId);
    setParentUserName(parentUserName);
    nextStep();
  };

  const onNextKidFormAmount = (selectedAmount: number) => {
    setAmount(selectedAmount);
    console.log("금액:", selectedAmount);
    nextStep();
  };

  const onNextFormMsg = (message: string) => {
    setContent(message);
    console.log("메세지:", message);
    nextStep(message);
  };

  const nextStep = (message?: string) => {
    if (step === 3) {
      fetch("http://j9d209.p.ssafy.io:8081/api/pocketmoney/request", {
        method: "POST",
        headers: {
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjkxODMxMzczMDc2Njg4NTAwMDAsInJvbGUiOiJDSElMRCIsImlhdCI6MTY5NTg4NDg2NCwiZXhwIjoxNjk1OTI4MDY0fQ.0BpIXU9V-Zon3FclpY4TB_3MDGu8SPpiRrz9pNYbc7g",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          parentUserId,
          amount,
          content: message || content,
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
      form = <ParentSelectForm onNext={onNextParentSelectForm} />;
      break;
    case 2:
      form = <KidFormAmount onNext={onNextKidFormAmount} />;
      break;

    case 3:
      if (parentUserName && amount) {
        form = (
          <KidFormMsg
            pname={parentUserName}
            dmoney={amount}
            onNext={onNextFormMsg}
          />
        );
      } else {
        form = <KidFormMsg pname="보호자" dmoney={0} onNext={onNextFormMsg} />;
      }
      break;

    case 4:
      form = (
        <MoneyDone
          title="용돈 요청 완료"
          content={
            <>
              <div className="text-m">{parentUserName}</div>
              <div className="text-m text-main">{amount}원</div>
            </>
          }
          ps="보호자가 2일동안 응답하지 않으면 취소돼요."
        />
      );
      break;
    default:
      throw new Error("Invalid step");
  }

  return <div className="m-10">{form}</div>;
}

export default KidDemandMoney;
