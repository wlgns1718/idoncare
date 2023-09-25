import React, { useState } from "react";
import Header from "../common/Header";
import FullBtn from "../common/FullBtn";
import MyKidList from "../pocketmoney/MyKidList";

interface Props {
  onNext: () => void;
}

const KidSelectForm: React.FC<Props> = ({ onNext }) => {
  const [kidCount, setKidCount] = useState(0);

  return (
    <div className="flex flex-col h-screen pb-60">
      <Header pageTitle="용돈 보내기" headerType="normal" headerLink="/" />

      <div className="m-10 text-center flex-grow">
        {kidCount > 0 && <div className="text-l mt-24 mb-28">자녀를 선택해주세요.</div>}

        <MyKidList onKidsSelected={setKidCount} />
      </div>
      {kidCount > 0 && <FullBtn buttonText="다음" onClick={onNext} />}
    </div>
  );
};

export default KidSelectForm;
