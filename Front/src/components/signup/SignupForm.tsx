import { useState } from "react";
import SignupQuestion from "./SignupQuestion";
import { SignupUserInfo } from "../../types/SignupUserInfo";
import SignupTypeSelect from "./SignupTypeSelect";

const SignupForm = () => {
  const [step, setStep] = useState<number>(1);
  const [signupUserInfo, setSignupUserInfo] = useState<SignupUserInfo>();
  const nextStep = () => {
    setStep((prev) => prev + 1);
  };
  const setType = (type: string) => {
    setSignupUserInfo((prev) => ({ ...prev, type }));
  };

  console.log(signupUserInfo);
  return (
    <div className="flex items-center justify-around h-full text-center text-s">
      {step === 1 && (
        <SignupTypeSelect
          userType={signupUserInfo?.type}
          onNextStep={nextStep}
          onSetType={setType}
        />
      )}
      {step === 2 && <SignupQuestion onNextStep={nextStep} question="이름을 알려주세요." />}
      {step === 3 && <SignupQuestion onNextStep={nextStep} question="닉네임을 알려주세요." />}
      {step === 4 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="생년월일을 알려주세요."
          caption="예) 20101231"
        />
      )}
      {step === 5 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="전화번호를 알려주세요."
          caption="예) 01012345678"
        />
      )}
      {step === 6 && <SignupQuestion onNextStep={nextStep} question="이메일 주소를 알려주세요." />}
      {step === 7 && <SignupQuestion onNextStep={nextStep} question="주소를 알려주세요." />}
      {step === 8 && <SignupQuestion onNextStep={nextStep} question="계좌가 있으신가요?" />}
    </div>
  );
};

export default SignupForm;
