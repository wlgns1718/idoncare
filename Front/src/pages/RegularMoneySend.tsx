import React, { useState } from 'react';
import RegularMoneySendForm from '../components/pocketmoney/RegularSendForm'
import MoneyDone from '../components/pocketmoney/Done';

const SendRegularMoney: React.FC = () => {
  const [step, setStep] = useState(1);

  const nextStep = () => setStep(step + 1);

  console.log("Current step:", step);

  let form;
  switch (step) {
    case 1:
      form = <RegularMoneySendForm onNext={nextStep} />;
      break;
    case 2:
      form = <MoneyDone 
                title="정기용돈 등록 완료"
                content={
                  <>
                    <div className="text-m">이우철</div>
                    <div className="text-m">매월 1일 <span className="text-main">1,000원</span></div>
                  </>
                }
                ps="충전잔액이 부족하면 이체되지 않습니다."
              />
      break;
    default:
        throw new Error('Invalid step');
}

return (
    <div>
        {form}
    </div>
   );
};

export default SendRegularMoney;
