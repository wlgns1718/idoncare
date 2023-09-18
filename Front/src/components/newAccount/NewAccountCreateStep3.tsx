import { useState } from "react";
import FullBtn from "../common/FullBtn";
import NewAccountCheckBox from "./NewAccountCheckBox";
import NewAccountHeader from "./NewAccountHeader";
import NewAccountInput from "./NewAccountInput";
import NewAccountSelectBox from "./NewAccountSelectBox";
import NewAccountToggleButton from "./NewAccountToggleButton";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";

const NewAccountCreateStep3 = ({ onChangeStep, step }: NewAccountCreate) => {
  const [serviceAgree, setServiceAgree] = useState(false);
  const [privateAgree, setPrivateAgree] = useState(false);
  const [local, setLocal] = useState(true);
  const [gender, setGender] = useState(true);
  const handleServiceAgree = () => setServiceAgree(!serviceAgree);
  const handlePrivateAgree = () => setPrivateAgree(!privateAgree);
  const handleLocal = (data: boolean) => setLocal(data);
  const handleGender = (data: boolean) => setGender(data);
  return (
    <div className="flex flex-col text-m">
      <NewAccountHeader step={1} />
      <p className="text-s mb-[10px]">제공되는 정보 : 성명, 연계정보(CI)값</p>
      <div className="flex items-center text-m">
        <NewAccountInput placeholder="성명" />
        <NewAccountToggleButton
          first="내국인"
          second="외국인"
          isLeft={local}
          onChange={handleLocal}
        />
      </div>
      <div className="flex items-center text-m">
        <NewAccountInput placeholder="생년월일(8자리)" />
        <NewAccountToggleButton first="남" second="여" isLeft={gender} onChange={handleGender} />
      </div>
      <NewAccountInput placeholder="휴대폰번호(숫자만)" />
      <NewAccountSelectBox />
      <NewAccountCheckBox
        text="서비스 이용 및 개인정보처리 동의"
        isCheck={serviceAgree}
        onToggle={handleServiceAgree}
      />
      <NewAccountCheckBox
        text="개인정보 제3자 제공 동의"
        isCheck={privateAgree}
        onToggle={handlePrivateAgree}
      />
      <div onClick={() => onChangeStep(2)}>
        <FullBtn buttonText="다음" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountCreateStep3;
