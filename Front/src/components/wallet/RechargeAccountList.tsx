import FullBtn from "../common/FullBtn";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

const EmptyAccount = () => {
  const navigate = useNavigate();
  return (
    <div className="w-full h-[80px] bg-gray rounded-xl flex-col flex items-center justify-center my-4">
      <div>충전 계좌 추가</div>
      <div
        onClick={() => {
          navigate("regist");
        }}
      >
        +
      </div>
    </div>
  );
};

const RechargeAccountComponent = () => {
  return (
    <div className="w-full h-[80px] px-8 my-4 bg-gray rounded-xl flex items-center  justify-between ">
      <div className="flex items-center">
        <div className="mx-8">아이콘</div>
        <div className="flex-col justify-center flex">
          <div>NH 농협</div>
          <div>12983723498732</div>
        </div>
      </div>
      <div>▼</div>
    </div>
  );
};

const tempRechargeAccount = {
  name: "NH 농협",
  accountNumber: 12983723498732,
};

interface RechargeAccountType {
  name: string;
  accountNumber: number;
}

function RechargeAccountList() {
  const navigate = useNavigate();

  const [rechargeAccount, setRechargeAccount] =
    useState<RechargeAccountType>(tempRechargeAccount);
  return (
    <div>
      <div className="flex justify-between">
        <div>출금 계좌</div>
        {!rechargeAccount && (
          <div className="inline-flex items-center rounded-md bg-red-50 px-2 p-1 text-xs font-medium text-red-700 ring-1 ring-inset ring-red-600/10">
            계좌 삭제
          </div>
        )}
      </div>
      <div className="">
        {rechargeAccount ? <EmptyAccount /> : <RechargeAccountComponent />}
      </div>
      <FullBtn buttonText="충전" buttonLink="password" />
    </div>
  );
}

export default RechargeAccountList;
