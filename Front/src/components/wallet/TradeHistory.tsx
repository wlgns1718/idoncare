import { useState } from "react";
import { useNavigate } from "react-router-dom";
import DailyTradeList from "./DailyTradeList";
import Chip from "../common/Chip";
import Icon from "../common/Icon";
import { TradeHistoryCategory } from "../../types/WalletTypes";

function TradeHistory() {
  const navigate = useNavigate();

  const [currentCategory, setCurrentCategory] = useState<string>("ALL");

  const handleCategory = (categoryValue: string) => {
    setCurrentCategory(categoryValue);
  };

  const categorys: TradeHistoryCategory[] = [
    {
      type: "ALL",
      text: "전체",
    },
    {
      type: "IN",
      text: "입금",
    },
    {
      type: "OUT",
      text: "출금",
    },
  ];

  return (
    <div>
      <div className="flex py-6 justify-between pr-5">
        <div className="flex gap-5 overflow-x-auto no-scrollbar">
          {categorys.map((category, index) => (
            <div className="flex-none" key={index}>
              <Chip
                isSelected={currentCategory === category.type}
                category={category}
                handler={handleCategory}
              />
            </div>
          ))}
        </div>
        <button className="w-[20px]" onClick={() => navigate("search")}>
          <Icon name="search" size="small" />
        </button>
      </div>
      <div className="bg-gray h-16 rounded-xl flex justify-between p-4 items-center">
        <button
          onClick={() => {
            console.log(1);
          }}
          className=""
        >
          <Icon name="chevron-left" />
        </button>
        <div className="text-s">{"2023년 09월"}</div>
        <button
          onClick={() => {
            console.log(1);
          }}
          className="opacity-50"
          disabled
        >
          <Icon name="chevron-right" />
        </button>
      </div>
      <div>
        {/* 날짜별 */}
        <DailyTradeList tradeList={[]}/>
      </div>
    </div>
  );
}

export default TradeHistory;
