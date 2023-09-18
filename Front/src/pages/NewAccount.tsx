import { useState } from "react";
import NewAccountCreateStep1 from "../components/newAccount/NewAccountCreateStep1";
import NewAccountCreateStep2 from "../components/newAccount/NewAccountCreateStep2";
import NewAccountCreateStep3 from "../components/newAccount/NewAccountCreateStep3";
import NewAccountCreateStep4 from "../components/newAccount/NewAccountCreateStep4";
import Header from "../components/common/Header";

const NewAccount = () => {
  const [step, setStep] = useState(1);
  const handleStep = (data: number) => setStep(data);
  return (
    <>
      <Header pageTitle="오픈뱅킹" headerType="normal" headerLink="/" />
      {step === 1 && <NewAccountCreateStep1 onChangeStep={handleStep} step={step} />}
      {step === 2 && <NewAccountCreateStep2 onChangeStep={handleStep} step={step} />}
      {step === 3 && <NewAccountCreateStep3 onChangeStep={handleStep} step={step} />}
      {step === 4 && <NewAccountCreateStep4 onChangeStep={handleStep} step={step} />}
    </>
  );
};

export default NewAccount;
