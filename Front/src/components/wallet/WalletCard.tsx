import React from "react";
import Icon from "../common/Icon";
import { useNavigate } from "react-router-dom";

function WalletCard() {
  const navigate = useNavigate();
  return (
    <div className="p-10 text-white h-[140px] rounded-2xl [background:linear-gradient(270deg,_#1c51ad_20%,_rgba(28,_81,_173,_0.3))]">
      <div className="ml-3">
        <div>잔액</div>
        <div className="text-m">99,000 원</div>
      </div>
      <div className="flex justify-between px-6 py-5 text-center">
        <div>
          <div className="w-10 h-10 m-auto">
            <Icon size="small" />
          </div>
          <div>용돈 관리</div>
        </div>
        <div onClick={() => navigate("recharge")}>
          <div className=" w-10 h-10 m-auto">
            <Icon size="small" />
          </div>
          <div>충전</div>
        </div>
        <div onClick={() => navigate("/transfer/account")}>
          <div className="w-10 h-10 m-auto">
            <Icon size="small" />
          </div>
          <div>송금</div>
        </div>
      </div>
    </div>
  );
}

export default WalletCard;
