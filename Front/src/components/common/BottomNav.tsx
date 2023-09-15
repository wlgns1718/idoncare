import React from "react";
import Icon from "./Icon";

const BottomTaps = [
  {
    icon: <Icon name="purchase" size="large" />,
    link: "/purchase",
    text: "결제",
  },
  {
    icon: <Icon name="home" size="large" />,
    link: "/home",
    text: "홈",
  },
  {
    icon: <Icon name="total" size="large" />,
    link: "/total",
    text: "전체",
  },
];

function BottomNav() {
  const iconWidth = "60px";
  return (
    <div className="fixed bottom-0 left-0 w-full bg-white rounded-t-3xl border-t-2 p-4 flex justify-evenly ">
      {BottomTaps.map((item, index) => {
        return (
          <div
            className={`h-[${iconWidth}] w-[${iconWidth}] flex-col content-center flex justify-center text-center items-center`}
            key={index}
          >
            {item.icon}
            <div className="text-m">{item.text}</div>
          </div>
        );
      })}
    </div>
  );
}

export default BottomNav;
