import { useNavigate } from "react-router-dom";
import { rechargeAccount } from "../../store/wallet/atoms";
import { useRecoilValue, useSetRecoilState } from "recoil";
import { isExistRechargeAccount } from "../../store/wallet/selectors";
import { useEffect } from "react";
import axios from "axios";
import { userToken } from "../../store/common/atoms";
import { RechargeAccountResponse } from "../../types/WalletTypes";

export const EmptyAccount = () => {
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

export const RechargeAccountComponent = () => {
  const myRechargeAccount = useRecoilValue(rechargeAccount);
  return (
    <div className="w-full h-[80px] px-8 my-4 bg-gray rounded-xl flex items-center justify-between ">
      <div className="flex items-center">
        <img
          className="w-[8vw] mx-4"
          src={`https://port-0-openbankapi-iciy2almk8xusg.sel5.cloudtype.app/images/${myRechargeAccount?.bankName}.png`}
        ></img>
        <div className="flex-col justify-center flex">
          <div>{myRechargeAccount?.bankName}</div>
          <div>{myRechargeAccount?.realAccountId}</div>
        </div>
      </div>
    </div>
  );
};

function RechargeAccountList() {
  const Token = useRecoilValue(userToken);
  const setRechargeAccount = useSetRecoilState(rechargeAccount);
  useEffect(() => {
    if (!isRechargeAccount) {
      axios
        .get(`http://j9d209.p.ssafy.io:8081/api/account/my`, {
          headers: { Authorization: Token as string },
        })
        .then((res) => {
          console.log(res.data);
          const account: RechargeAccountResponse = res.data.data;
          setRechargeAccount({
            realAccountId: account.realAccountId,
            pinNumber: account.pinNumber,
            bankName: account.bankName,
            bankCode: account.bankCode,
          });
        });
    }
  }, []);

  const isRechargeAccount = useRecoilValue(isExistRechargeAccount);

  return (
    <div className="mx-8">
      <div className="flex justify-between">
        <div>출금 계좌</div>
        {isRechargeAccount && (
          <div className="inline-flex items-center rounded-md bg-red-50 px-2 p-1 text-xs font-medium text-red-700 ring-1 ring-inset ring-red-600/10">
            계좌 삭제
          </div>
        )}
      </div>
      <div className="">
        {!isRechargeAccount ? <EmptyAccount /> : <RechargeAccountComponent />}
      </div>
    </div>
  );
}

export default RechargeAccountList;
