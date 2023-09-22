import Icon, { ICON_NAME } from "../common/Icon";
import { useNavigate } from "react-router-dom";

type cardButtonType = {
  text: string;
  icon: ICON_NAME;
  link: string;
};

const cardButton: cardButtonType[] = [
  {
    link: "/pocketMoney",
    text: "용돈관리",
    icon: "home",
  },
  {
    link: "recharge",
    text: "충전",
    icon: "home",
  },
  {
    link: "/transfer/account",
    text: "송금",
    icon: "home",
  },
];

function WalletCard() {
  const navigate = useNavigate();
  return (
    <div className="bg-white rounded-2xl overflow-hidden">
      <div className="p-10 text-white h-[140px] rounded-2xl [background:linear-gradient(270deg,_#1c51ad_20%,_rgba(28,_81,_173,_0.3))]">
        <div
          className="ml-3"
          onClick={() => {
            navigate("/wallet");
          }}
        >
          <div>잔액</div>
          <div className="text-m">99,000 원</div>
        </div>
        <div className="flex justify-between px-6 py-5 text-center">
          {cardButton.map((item, index) => {
            return (
              <div
                key={index}
                className="h-10"
                onClick={() => navigate(item.link)}
              >
                <div className="px-2">
                  <Icon size="small" name={item.icon} />
                </div>
                <div className="w-16">{item.text}</div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default WalletCard;
