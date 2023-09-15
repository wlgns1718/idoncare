import { useNavigate } from "react-router-dom";
import Icon from "./Icon";

const BottomTaps = [
  {
    icon: <Icon name="purchase" size="medium" />,
    link: "/purchase",
    text: "결제",
  },
  {
    icon: <Icon name="home" size="medium" />,
    link: "/",
    text: "홈",
  },
  {
    icon: <Icon name="total" size="medium" />,
    link: "/total",
    text: "전체",
  },
];

function BottomNav() {
  const navigate = useNavigate();
  return (
    <div>
      <div className="h-[40px]"></div>
      <div className="fixed bottom-0 left-0 w-full bg-white rounded-t-3xl border-t-2 pt-2 flex justify-evenly">
        {BottomTaps.map((item, index) => {
          return (
            <div
              className={`flex-col content-center flex justify-center text-center items-center`}
              key={index}
              onClick={()=>{navigate(item.link)}}
            >
              <div className="w-[8vw]">{item.icon}</div>
              <div className="text-m">{item.text}</div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default BottomNav;
