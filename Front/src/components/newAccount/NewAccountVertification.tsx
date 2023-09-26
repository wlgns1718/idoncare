import FullBtn from "../common/FullBtn";
import NewAccountHeader from "./common/NewAccountHeader";
import NewAccountInput from "./common/NewAccountInput";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";
import NewAccountVertificationAccount from "./NewAccountVertification/NewAccountVertificationAccount";
import NewAccountVertificationHelp from "./NewAccountVertification/NewAccountVertificationHelp";
import { useRecoilValue } from "recoil";
import { userToken } from "../../store/common/atoms";
import axios from "axios";

const NewAccountVertification = ({ onChangeStep, step }: NewAccountCreate) => {
  const token = useRecoilValue(userToken);

  const registAccount = () => {
    axios
      .post(
        `http://j9d209.p.ssafy.io:8081/api/account/regist`,
        {
          bankCodeStd: "51",
          accountNum: "1234567890",
          bankTranId: "T9916764",
          accountHolderInfo: "20000101",
          tranDtime: "2023-09-26T19:05:16.539Z",
        },
        {
          headers: { Authorization: token as string },
        }
      )
      .then((res) => {
        console.log(res.data);
      });
  };

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
