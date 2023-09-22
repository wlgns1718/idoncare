import React from "react";
import Header from "../common/Header";
import FullBtn from "../common/FullBtn";
import MyKidList from "../pocketmoney/MyKidList";

interface Props {
  onNext: () => void;
}

const KidSelectForm: React.FC<Props> = ({ onNext }) => {
  return (
    <div className="flex flex-col h-screen pb-60">
      <Header pageTitle="용돈 보내기" headerType="normal" headerLink="/" />

      <div className="m-10 text-center flex-grow">
        <div className="text-l mt-24 mb-28">자녀를 선택해주세요.</div>

        <MyKidList />
      </div>
      <FullBtn buttonText="다음" onClick={onNext} />
    </div>
  );
};

export default KidSelectForm;
