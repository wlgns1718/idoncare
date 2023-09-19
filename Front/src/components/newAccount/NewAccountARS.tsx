import FullBtn from "../common/FullBtn";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";

import NewAccountInput from "./common/NewAccountInput";
import NewAccountARSHelp from "./NewAccountARS/NewAccountARSHelp";
import NewAccountText from "./NewAccountARS/NewAccountText";

const NewAccountARS = ({ onChangeStep, step }: NewAccountCreate) => {
  console.log(step);
  return (
    <div className="flex flex-col text-m">
      <NewAccountARSHelp />
      <div className="flex flex-col items-center justify-center bg-gray pt-[20px] pb-[10px] text-s px-[10px] my-[10px]">
        <NewAccountText arsTextIndex={0} />
        <NewAccountInput placeholder="example@idoncare.co.kr" />
        <NewAccountText arsTextIndex={1} />
      </div>
      <div onClick={() => onChangeStep(5)}>
        <FullBtn buttonText="다음" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountARS;
