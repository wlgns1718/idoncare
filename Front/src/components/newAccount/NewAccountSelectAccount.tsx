import { useState } from "react";
import FullBtn from "../common/FullBtn";
import NewAccountCheckBox from "./common/NewAccountCheckBox";
import NewAccountHeader from "./common/NewAccountHeader";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../../store/common/atoms";
import AccountSelectForm from "../wallet/AccountSelectForm";
import {
  resistRechargeAccountInput,
  sendAccountBank,
} from "../../store/wallet/atoms";

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

  const accountNum = useRecoilValue(resistRechargeAccountInput);
  const bank = useRecoilValue(sendAccountBank);

  const accountValidCheck = () => {
    axios
      .post(
        `http://j9d209.p.ssafy.io:8081/api/account/valid`,
        {
          bankCodeStd: bank?.bankId,
          bankName: bank?.bankName,
          accountNum: accountNum,
        },
        {
          headers: { Authorization: token as string },
        }
      )
      .then((res) => {
        console.log(res.data);
        if (res.data == null) {
          console.log(res.data.error);
          return;
        }
        onChangeStep(3);
      });
  };

  return (
    <div className="flex flex-col text-m">
      <NewAccountHeader step={step} />
      <AccountSelectForm btn={false} />
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
          accountValidCheck();
        }}
      >
        <FullBtn
          buttonText="다음"
          isDone={withdrawServiceAgree && finDataAgree && privateFinDataAgree}
        />
      </div>
    </div>
  );
};

export default NewAccountSelectAccount;
