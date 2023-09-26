import { useState } from "react";
import FullBtn from "../common/FullBtn";
import NewAccountCheckBox from "./common/NewAccountCheckBox";
import NewAccountHeader from "./common/NewAccountHeader";
import NewAccountInput from "./common/NewAccountInput";
import NewAccountSelectBox from "./common/NewAccountSelectBox";
import NewAccountToggleButton from "./common/NewAccountToggleButton";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";
import axios from "axios";
import { userToken } from "../../store/common/atoms";
import { useRecoilValue } from "recoil";

const NewAccountUserInfo = ({ onChangeStep, step }: NewAccountCreate) => {
  const [serviceAgree, setServiceAgree] = useState(false);
  const [privateAgree, setPrivateAgree] = useState(false);
  const [local, setLocal] = useState(true);
  const [gender, setGender] = useState(true);
  const handleServiceAgree = () => setServiceAgree(!serviceAgree);
  const handlePrivateAgree = () => setPrivateAgree(!privateAgree);
  const handleLocal = (data: boolean) => setLocal(data);
  const handleGender = (data: boolean) => setGender(data);

  const token = useRecoilValue(userToken);

  const userAuthentication = () => {
    axios
      .post(
        `http://j9d209.p.ssafy.io:8081/api/account/auth`,
        {
          phoneNumber: "01012345678",
          birth: "19900101",
          mobileSort: "SK",
          sex: "female",
          name: "김엄마",
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
        <NewAccountToggleButton
          first="남"
          second="여"
          isLeft={gender}
          onChange={handleGender}
        />
      </div>
      <NewAccountInput placeholder="휴대폰번호(숫자만)" />
      <NewAccountSelectBox step={step} />
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
      <div
        onClick={() => {
          onChangeStep(2);
          userAuthentication();
        }}
      >
        <FullBtn buttonText="다음" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountUserInfo;
