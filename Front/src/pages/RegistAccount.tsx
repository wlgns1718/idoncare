import Header from "../components/common/Header";
import FullBtn from "../components/common/FullBtn";
import AccountSelectForm from "../components/wallet/AccountSelectForm";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/atoms";

function RegistAccount() {
  const Token = useRecoilValue(userToken);

  const registRechargeAccount = () => {
    axios
      .post(`http://j9d209.p.ssafy.io:8081/api/account/regist`, {
        headers: { Authorization: Token as string },
      })
      .then((res) => {
        console.log(res.data);
      });
  };
  return (
    <div>
      <Header pageTitle="충전 계좌 등록" />
      <AccountSelectForm />
      <FullBtn onClick={registRechargeAccount} buttonText="등록" />
    </div>
  );
}

export default RegistAccount;
