import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import DailyTradeList from "./DailyTradeList";
import Chip from "../common/Chip";
import Icon from "../common/Icon";
import {
  CashFlow,
  TradeHistoryCategory,
  TradeItem,
} from "../../types/WalletTypes";
import axios from "axios";
import { userToken } from "../../store/common/atoms";
import { useRecoilValue } from "recoil";
import { baseUrl } from "../../apis/url/baseUrl";

export interface MonthlyTradeListResponse {
  date: number;
  historyList: Array<TradeItem>;
}

function TradeHistory() {
  const navigate = useNavigate();

  const [currentCategory, setCurrentCategory] = useState<CashFlow>("ALL");
  const Token = useRecoilValue(userToken);

  const [currentDate, setCurrentDate] = useState(new Date());

  const today = new Date();
  const thisYear = today.getFullYear();
  const thisMonth = today.getMonth();

  useEffect(() => {
    getTradeHistory();
  }, [currentDate]);

  const handleCategory = (categoryValue: string | CashFlow) => {
    setCurrentCategory(categoryValue as CashFlow);
  };

  const isNowMonth = () => {
    return (
      thisYear === currentDate.getFullYear() &&
      thisMonth === currentDate.getMonth()
    );
  };

  const handleNextMonth = () => {
    setCurrentDate((prevMonth) => {
      const nextMonth = new Date(prevMonth);
      nextMonth.setMonth(prevMonth.getMonth() + 1);
      return nextMonth;
    });
  };

  const handlePrevMonth = () => {
    setCurrentDate((prevMonth) => {
      const newPrevMonth = new Date(prevMonth);
      newPrevMonth.setMonth(prevMonth.getMonth() - 1);
      return newPrevMonth;
    });
  };

  const [monthlyTradeList, setMonthlyTradeList] = useState<
    MonthlyTradeListResponse[]
  >([]);

  const getTradeHistory = () => {
    axios
      .get(
        baseUrl +
          `api/virtual/${currentDate.getFullYear()}/${
            currentDate.getMonth() + 1
          }`,
        { headers: { Authorization: Token } }
      )
      .then((response) => {
        console.log(response.data.data);
        if (response.data.data) {
          setMonthlyTradeList(response.data.data);
        } else {
          setMonthlyTradeList([]);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const categorys: TradeHistoryCategory[] = [
    {
      type: "ALL",
      text: "전체",
    },
    {
      type: "DEPOSIT",
      text: "입금",
    },
    {
      type: "WITHDRAWAL",
      text: "출금",
    },
  ];

  return (
    <>
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
            handlePrevMonth();
          }}
          className=""
        >
          <Icon name="chevron-left" />
        </button>
        <div className="text-s">
          {currentDate.getFullYear()}년 {currentDate.getMonth() + 1}월
        </div>
        <button
          onClick={() => {
            handleNextMonth();
          }}
          className={`${isNowMonth() ? "opacity-50" : ""}`}
          disabled={isNowMonth()}
        >
          <Icon name="chevron-right" />
        </button>
      </div>
      {/* 날짜별 */}
      {monthlyTradeList && (
        <div>
          {monthlyTradeList.map((list, index) => {
            return (
              <DailyTradeList
                key={index}
                list={list}
                showCategory={currentCategory}
              />
            );
          })}
        </div>
      )}
      {!monthlyTradeList && (
        <div className="p-10 text-m text-center text-main">
          거래 내역이 없습니다..
        </div>
      )}
    </>
  );
}

export default TradeHistory;
