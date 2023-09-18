import FullBtn from "../common/FullBtn";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";

const NewAccountCreateStep4 = ({ onChangeStep, step }: NewAccountCreate) => {
  console.log(step);
  return (
    <div className="flex flex-col text-m">
      <div onClick={() => onChangeStep(5)}>
        <FullBtn buttonText="다음" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountCreateStep4;
