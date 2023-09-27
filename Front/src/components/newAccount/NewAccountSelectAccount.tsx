import { useState } from "react";
import FullBtn from "../common/FullBtn";
import NewAccountCheckBox from "./common/NewAccountCheckBox";
import NewAccountHeader from "./common/NewAccountHeader";
import NewAccountInput from "./common/NewAccountInput";
import NewAccountSelectBox from "./common/NewAccountSelectBox";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../../store/common/atoms";

const NewAccountSelectAccount = ({ onChangeStep, step }: NewAccountCreate) => {
  const [withdrawServiceAgree, setWithdrawServiceAgree] = useState(false);
  const [finDataAgree, setFinDataAgree] = useState(false);
  const [privateFinDataAgree, setPrivateFinDataAgree] = useState(false);
  const handleWithdrawServiceAgree = () => setWithdrawServiceAgree(!withdrawServiceAgree);
  const handleFinDataAgree = () => setFinDataAgree(!finDataAgree);
  const handlePrivateFinDataAgree = () => setPrivateFinDataAgree(!privateFinDataAgree);

  const token = useRecoilValue(userToken);


  const accountValidCheck = () => {
    axios
      .get(
        `http://j9d209.p.ssafy.io:8081/api/account`,
        {
          bankCodeStd: "01012345678",
          accountNum: "19900101",
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
      <NewAccountSelectBox step={step} />
      <NewAccountInput placeholder="계좌번호 (숫자만)" />
      <NewAccountCheckBox
        text="출금서비스(은행) 약관 동의"
        isCheck={withdrawServiceAgree}
        onToggle={handleWithdrawServiceAgree}
      />
      <NewAccountCheckBox
        text="금융정보조회 이용 동의"
        isCheck={finDataAgree}
        onToggle={handleFinDataAgree}
      />
      <NewAccountCheckBox
        text="금융정보 제3자 제공 동의"
        isCheck={privateFinDataAgree}
        onToggle={handlePrivateFinDataAgree}
      />
      <div onClick={() => onChangeStep(3)}>
        <FullBtn buttonText="다음" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountSelectAccount;
