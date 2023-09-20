import React, { FC } from 'react';
import Header from "../common/Header";
import NumberPlate from '../common/NumberPlate';
import FullBtn from '../common/FullBtn';

interface Props {
  onNext: () => void;
}

const SendPocketMoneyForm: FC<Props> = ({ onNext }) => {
  return (
    <div className="flex flex-col h-screen pb-60">
        <Header pageTitle="용돈 보내기" headerType="normal" headerLink="/" />
        
        <div className="m-10 text-center flex-grow">
            <div className="text-l mt-24 mb-20">얼마를 보낼래요?</div>

            <div className="text-l text-main font-strong mb-5">1,000원</div>

            <div className="text-darkgray text-sm mb-10">
                잔액 102,000원
            </div>
        </div>

        <div className='mt-auto text-center'>
          <p>잔액 버튼 | 잔액 버튼 | 잔액 버튼</p>
          <NumberPlate bottomLeftText="00"/>

        <FullBtn buttonText="다음" onClick={onNext} />
    </div>
    </div>
  );
}

export default SendPocketMoneyForm;
