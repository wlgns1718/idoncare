import Icon from "../common/Icon";
import { useNavigate } from "react-router-dom";

function TopAccount() {
const navigate = useNavigate();
  return (
    <div className="bg-black bg-opacity-20 w-96 h-48 rounded-lg p-6">
        <div className="text-white text-t">잔액</div>
        <div className="text-white text-s">99,000원</div>
        
        <div className="flex justify-between px-6 py-5 text-center">
        <div>
          <div className="w-10 h-10 m-auto">
            <Icon />
          </div>
          <div>계좌 내역</div>
        </div>
        <div onClick={() => navigate("wallet/recharge")}>
          <div className=" w-10 h-10 m-auto">
            <Icon />
          </div>
          <div>충전</div>
        </div>
        <div>
          <div className="w-10 h-10 m-auto">
            <Icon />
          </div>
          <div>송금</div>
        </div>
      </div>

    </div>
  )
}

export default TopAccount