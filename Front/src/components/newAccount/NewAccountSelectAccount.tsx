import { useState } from "react";
import FullBtn from "../common/FullBtn";
import NewAccountCheckBox from "./common/NewAccountCheckBox";
import NewAccountHeader from "./common/NewAccountHeader";
import NewAccountInput from "./common/NewAccountInput";
import NewAccountSelectBox from "./common/NewAccountSelectBox";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";
import axios from "axios";
import { useRecoilState, useRecoilValue } from "recoil";
import { userToken } from "../../store/common/atoms";
import { bankValidationData } from "../../store/newAccount/atoms";

const NewAccountSelectAccount = ({ onChangeStep, step }: NewAccountCreate) => {
  const [withdrawServiceAgree, setWithdrawServiceAgree] = useState(false);
  const [finDataAgree, setFinDataAgree] = useState(false);
  const [privateFinDataAgree, setPrivateFinDataAgree] = useState(false);
  const handleWithdrawServiceAgree = () =>
    setWithdrawServiceAgree(!withdrawServiceAgree);
  const handleFinDataAgree = () => setFinDataAgree(!finDataAgree);
  const handlePrivateFinDataAgree = () =>
    setPrivateFinDataAgree(!privateFinDataAgree);

  const token = useRecoilValue(userToken);

  const [bankData, setBankData] = useRecoilState(bankValidationData);

  const accountValidCheck = () => {
    axios
      .get(`http://j9d209.p.ssafy.io:8081/api/account`, {
        headers: { Authorization: token as string },
        data: {
          bankCodeStd: "41",
          accountNum: "111111111111",
        },
      })
      .then((res) => {
        console.log(res.data);
      });
  };

  const handleBank = (value: number | string) =>
    setBankData({ ...bankData, bankCodeStd: value as number });
  const handleAccountNumber = (value: number | string) =>
    setBankData({ ...bankData, accountNum: value as number });

  return (
    <div className="flex flex-col text-m">
      <NewAccountHeader step={step} />
      <NewAccountSelectBox step={step} changeValue={handleBank} />
      <NewAccountInput
        placeholder="계좌번호 (숫자만)"
        changeValue={handleAccountNumber}
        value={bankData.accountNum}
      />
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
      <div
        onClick={() => {
          onChangeStep(3);
          accountValidCheck();
        }}
      >
        <FullBtn buttonText="다음" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountSelectAccount;
