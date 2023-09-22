import FullBtn from "../common/FullBtn";
import { useNavigate } from "react-router-dom";

function RechargeAccountList() {
  const navigate = useNavigate();
  
  return (
    <div>
      <div>출금 계좌</div>
      <div className="">
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
        <div className="w-full h-[80px] bg-gray rounded-xl flex-col flex items-center justify-center my-4">
          <div>충전 계좌 변경</div>
          <div onClick={()=>{navigate("regist");}}>아이콘</div>
        </div>
      </div>
      <FullBtn buttonText="충전" buttonLink="password" />
    </div>
  );
}

export default RechargeAccountList;
