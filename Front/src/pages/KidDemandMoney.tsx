import { useState } from "react";
import KidFormSelect from "../components/pocketmoney/kid/KidFormSelect";
import KidFormAmount from "../components/pocketmoney/kid/KidFormAmount"; // Import your component
import KidFormMsg from "../components/pocketmoney/kid/KidFormMsg"; // Import your component
import MoneyDone from "../components/pocketmoney/Done";

function KidDemandMoney() {
  const [step, setStep] = useState(1);

  const nextStep = () => setStep(step + 1);

  console.log("Current step:", step);

  let form;
  switch (step) {
    case 1:
      form = <KidFormSelect onNext={nextStep} />;
      break;
    case 2:
      form = <KidFormAmount onNext={nextStep} />;
      break;
    case 3:
      form = <KidFormMsg onNext={nextStep} />;
      break;
    case 4:
      form = (
        <MoneyDone
          title="용돈 요청 완료"
          content={
            <>
              <div className="text-m">엄마</div>
              <div className="text-m text-main">2,500원</div>
            </>
          }
          ps="2023.09.10일까지 응답하지 않으면 취소돼요."
        />
      );
      break;
    default:
      throw new Error("Invalid step");
  }

  return <div className="m-10">{form}</div>;
}

export default KidDemandMoney;
