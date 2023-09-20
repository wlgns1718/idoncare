import FullBtn from "../common/FullBtn";
import NewAccountHeader from "./common/NewAccountHeader";
import NewAccountInput from "./common/NewAccountInput";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";
import NewAccountVertificationAccount from "./NewAccountVertification/NewAccountVertificationAccount";
import NewAccountVertificationHelp from "./NewAccountVertification/NewAccountVertificationHelp";

const NewAccountVertification = ({ onChangeStep, step }: NewAccountCreate) => {
  return (
    <div className="flex flex-col text-m">
      <NewAccountHeader step={step} />
      <NewAccountVertificationAccount />
      <NewAccountVertificationHelp />
      <NewAccountInput placeholder="입금자명(숫자4자리)" />
      <div onClick={() => onChangeStep(4)}>
        <FullBtn buttonText="인증완료" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountVertification;
