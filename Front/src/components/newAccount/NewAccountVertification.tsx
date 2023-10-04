import FullBtn from "../common/FullBtn";
import NewAccountHeader from "./common/NewAccountHeader";
import NewAccountInput from "./common/NewAccountInput";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";
import NewAccountVertificationAccount from "./NewAccountVertification/NewAccountVertificationAccount";
import NewAccountVertificationHelp from "./NewAccountVertification/NewAccountVertificationHelp";
import { useRecoilValue } from "recoil";
import { userToken } from "../../store/common/selectors";
import axios from "axios";
import { useState } from "react";
import { baseUrl } from "../../apis/url/baseUrl";
import AxiosHeader from "../../apis/axios/AxiosHeader";

const NewAccountVertification = ({ onChangeStep, step }: NewAccountCreate) => {
  const [authenticationNumber, setAuthenticationNumber] = useState<number>();
  const token = useRecoilValue(userToken);

  const handleInputAccount = (value: string | number) => {
    setAuthenticationNumber(value as number);
  };

  const registAccount = () => {
    axios
      .post(
        baseUrl + `api/account/`,
        {
          bankCodeStd: 41,
          bankName: "신한은행",
          accountNum: 77777777,
        },
        AxiosHeader({ token })
      )
      .then((res) => {
        console.log(res.data);
        if (res.data.code == 404) {
          return;
        }
        onChangeStep(4);
      });
  };

  return (
    <div className="flex flex-col text-m">
      <NewAccountHeader step={step} />
      <NewAccountVertificationAccount />
      <NewAccountVertificationHelp />
      <NewAccountInput
        changeValue={handleInputAccount}
        value={authenticationNumber}
        placeholder="입금자명(숫자4자리)"
      />
      <div
        onClick={() => {
          registAccount();
        }}
      >
        <FullBtn buttonText="인증완료" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountVertification;
