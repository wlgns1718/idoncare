import { useNavigate } from "react-router-dom";
import { rechargeAccount } from "../../store/wallet/atoms";
import { useRecoilValue, useResetRecoilState, useSetRecoilState } from "recoil";
import { isExistRechargeAccount } from "../../store/wallet/selectors";
import { useEffect } from "react";
import axios from "axios";
import { userToken } from "../../store/common/selectors";
import { RechargeAccountResponse } from "../../types/WalletTypes";
import { baseUrl } from "../../apis/url/baseUrl";
import AxiosHeader from "../../apis/axios/AxiosHeader";

export const EmptyAccount = () => {
  const navigate = useNavigate();
  return (
    <div className="w-full h-[80px] bg-gray rounded-xl flex-col flex items-center justify-center my-4">
      <div>충전 계좌 추가</div>
      <div
        onClick={() => {
          navigate("/newAccount");
        }}
      >
        +
      </div>
    </div>
  );
};

export const RechargeAccountComponent = () => {
  const myRechargeAccount = useRecoilValue(rechargeAccount);
  const resetRechargeAccount = useResetRecoilState(rechargeAccount);
  const Token = useRecoilValue(userToken);

  const removeAccount = () => {

    axios
      .delete(baseUrl + `api/account/`, {
        headers: { Authorization: Token as string },
        data: {
          bankCode: myRechargeAccount?.bankCode,
          realAccountId: myRechargeAccount?.realAccountId,
        },
      })
      .then((res) => {
        console.log(res.data);
        resetRechargeAccount();
      });
  };
  return (
    <div className="w-full h-[80px] px-8 my-4 bg-gray rounded-xl flex items-center justify-between ">
      <div className="flex">
        <img
          className="w-[8vw] mx-4"
          src={`https://port-0-openbankapi-iciy2almk8xusg.sel5.cloudtype.app/images/${myRechargeAccount?.bankName}.png`}
        ></img>
        <div className="flex flex-col justify-center">
          <div>{myRechargeAccount?.bankName}</div>
          <div>{myRechargeAccount?.realAccountId}</div>
        </div>
      </div>
      <div
        className="flex items-center rounded-md bg-red-50 px-4 p-2 text-xs font-medium text-red-700 ring-1 ring-inset ring-red-600/20"
        onClick={() => {removeAccount()}}
      >
        삭제
      </div>
    </div>
  );
};

function RechargeAccountList() {
  const token = useRecoilValue(userToken);
  const setRechargeAccount = useSetRecoilState(rechargeAccount);
  useEffect(() => {
    if (!isRechargeAccount) {
      axios
        .get(
          `http://j9d209.p.ssafy.io:8081/api/account/my`,
          AxiosHeader({ token })
        )
        .then((res) => {
          console.log(res.data);
          if (res.data.error) {
            return;
          }
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
    <div className="my-6">
      <div className="flex justify-between">
        <div>출금 계좌</div>
      </div>
      <div className="">
        {!isRechargeAccount ? <EmptyAccount /> : <RechargeAccountComponent />}
      </div>
      {!isRechargeAccount && <div className="text-center text-red-600">충전 계좌가 등록되지 않았습니다</div>}
    </div>
  );
}

export default RechargeAccountList;
