import { useNavigate } from "react-router-dom";
import Header from "../components/common/Header";

const menus = [
  {
    Title: "현장에서 결제하기",
    subTitle: "QR코드로 결제",
    link: "qrcode",
    icon: "",
  },
  {
    Title: "카메라로 결제하기",
    subTitle: "QR코드 찍어서 결제",
    link: "camera",
    icon: "",
  },
];

function Purchase() {
  const navigate = useNavigate();
  return (
    <div>
      <Header pageTitle="결제" headerLink="/" headerType="normal" />
      <div className="mt-20">
        <div className="w-[190px] h-[110px] mx-auto p-4 rounded-lg [background:linear-gradient(270deg,_#1c51ad_20%,_rgba(28,_81,_173,_0.3))]">
          <div className="mb-10">아이콘</div>
          <div className="text-end items-end">
            <div className="text-white">부족금액은 자동충전됨</div>
            <div className="text-white">900 원</div>
          </div>
        </div>
        <div className="mt-20">
          {menus.map((item, index) => {
            return (
              <div
                className="w-full mt-8 p-7 rounded-xl bg-gray flex shadow-xl"
                key={index}
                onClick={()=>navigate(item.link)}
              >
                <div className="mx-6">
                  <img src={item.icon} alt="" />
                </div>
                <div>
                  <div className="text-gray-700">{item.Title}</div>
                  <div className="text-gray-500">{item.subTitle}</div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default Purchase;
