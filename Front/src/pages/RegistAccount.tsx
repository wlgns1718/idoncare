import Header from "../components/common/Header";
import FullBtn from "../components/common/FullBtn";
import AccountSelectForm from "../components/wallet/AccountSelectForm";


function RegistAccount() {

  return (
    <div>
      <Header pageTitle="충전 계좌 등록" />
      <AccountSelectForm />
      <FullBtn buttonText="등록"/>
    </div>
  );
}

export default RegistAccount;
