import React from "react";
import Header from "../components/common/Header";
import NumberPlate from "../components/common/NumberPlate";
import FullBtn from "../components/common/FullBtn";
import MoneyInputForm from "../components/common/MoneyInputForm";
import { useNavigate } from 'react-router-dom';

function Transfer() {
  const navigate = useNavigate();
  return (
    <div>
      <Header pageTitle="보내기"></Header>
      <MoneyInputForm text={"얼마를 보낼래요?"} balance={12300}/>
      <FullBtn buttonText="다음" onClick={() => {navigate("account");}} />
    </div>
  );
}

export default Transfer;
