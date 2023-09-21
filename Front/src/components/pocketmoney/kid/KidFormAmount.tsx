import React from "react";
import Header from "../../common/Header";
import FullBtn from "../../common/FullBtn";

interface Props {
  onNext: () => void;
}

const KidFormAmount: React.FC<Props> = ({ onNext }) => {
  return (
    <div className="flex flex-col h-screen pb-60">
      <Header pageTitle="용돈 조르기" headerType="normal" headerLink="/" />
      <div className="flex-grow">
        <div>얼마를 달라고 할까요?</div>
      </div>
      <FullBtn buttonText="다음" onClick={onNext} />
    </div>
  );
};

export default KidFormAmount;
