import React, { useState } from "react";
import Header from "../common/Header";
import FullBtn from "../common/FullBtn";
import Parents from "../connect/Parents";

interface Props {
  onNext: () => void;
}

const ParentSelectForm: React.FC<Props> = ({ onNext }) => {
  const [isParentSelected, setIsParentSelected] = useState(false);

  return (
    <div className="flex flex-col h-screen pb-60">
      <Header pageTitle="용돈 받기" headerType="normal" headerLink="/" />

      <div className="m-10 text-center flex-grow">
        {isParentSelected && <div className="text-l mt-24 mb-28">부모님을 선택해주세요.</div>}

        <Parents onParentSelected={setIsParentSelected} />
      </div>
      {/* {isParentSelected && <FullBtn buttonText="다음" onClick={onNext} />} */}
      <FullBtn buttonText="다음" onClick={onNext} isDone={false}/>
    </div>
  );
};

export default ParentSelectForm;
