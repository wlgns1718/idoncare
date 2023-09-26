import { useState } from "react";
import NewAccountCreateUserInfo from "../components/newAccount/NewAccountUserInfo";
import NewAccountSelectAccount from "../components/newAccount/NewAccountSelectAccount";
import NewAccountVertification from "../components/newAccount/NewAccountVertification";
// import NewAccountARS from "../components/newAccount/NewAccountARS";
import Header from "../components/common/Header";

const NewAccount = () => {
  const [step, setStep] = useState(1);
  const handleStep = (data: number) => setStep(data);
  return (
    <>
      <Header pageTitle="오픈뱅킹" headerType="normal" headerLink="/" />
      <div className="mx-8">
        {step === 1 && (
          <NewAccountCreateUserInfo onChangeStep={handleStep} step={step} />
        )}
        {step === 2 && (
          <NewAccountSelectAccount onChangeStep={handleStep} step={step} />
        )}
        {step === 3 && (
          <NewAccountVertification onChangeStep={handleStep} step={step} />
        )}
        {/* {step === 4 && <NewAccountARS onChangeStep={handleStep} step={step} />} */}
      </div>
    </>
  );
};

export default NewAccount;
