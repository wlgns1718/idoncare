import Header from "../components/common/Header";
import FullBtn from "../components/common/FullBtn";
import MoneyInputForm from "../components/common/MoneyInputForm";
import { useNavigate } from 'react-router-dom';
import { useRecoilValue } from "recoil";
import { userBalanace } from "../store/wallet/atoms";

function Transfer() {
  const navigate = useNavigate();

  const balance = useRecoilValue(userBalanace);
  return (
    <div>
      <Header pageTitle="보내기"></Header>
      <MoneyInputForm text={"얼마를 보낼래요?"} balance={balance} />
      <FullBtn
        buttonText="다음"
        onClick={() => {
          navigate("account");
        }}
      />
    </div>
  );
}

export default Transfer;
