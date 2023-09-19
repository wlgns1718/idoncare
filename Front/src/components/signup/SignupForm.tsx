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
  const handleSetInfo = (setType: number, value: string) => {
    if (setType === 1) {
      setSignupUserInfo((prev) => ({ ...prev, type: value }));
    } else if (setType === 2) {
      setSignupUserInfo((prev) => ({ ...prev, name: value }));
    } else if (setType === 3) {
      setSignupUserInfo((prev) => ({ ...prev, nickname: value }));
    } else if (setType === 4) {
      setSignupUserInfo((prev) => ({ ...prev, birth: value }));
    } else if (setType === 5) {
      setSignupUserInfo((prev) => ({ ...prev, email: value }));
    } else if (setType === 6) {
      setSignupUserInfo((prev) => ({ ...prev, address: value }));
    } else if (setType === 7) {
      setSignupUserInfo((prev) => ({ ...prev, phone: value }));
    } else if (setType === 8) {
      setSignupUserInfo((prev) => ({ ...prev, code: value }));
    } else if (setType === 9) {
      setSignupUserInfo((prev) => ({ ...prev, hasAccount: value }));
    }
  };

  console.log(signupUserInfo);

  return (
    <div className="flex items-center justify-around h-full text-center text-s">
      {step === 1 && (
        <SignupTypeSelect
          userType={signupUserInfo?.type}
          onNextStep={nextStep}
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
      {step === 2 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="이름을 알려주세요."
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
      {step === 3 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="닉네임을 알려주세요."
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
      {step === 4 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="생년월일을 알려주세요."
          caption="예) 20101231"
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
      {step === 5 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="이메일 주소를 알려주세요."
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
      {step === 6 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="주소를 알려주세요."
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
      {step === 7 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="전화번호를 알려주세요."
          caption="예) 01012345678"
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
      {step === 8 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="인증번호를 입력하세요."
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
      {step === 9 && (
        <SignupQuestion
          onNextStep={nextStep}
          question="계좌가 있으신가요?"
          onSetInfo={handleSetInfo}
          step={step}
        />
      )}
    </div>
  );
};

export default SignupForm;
