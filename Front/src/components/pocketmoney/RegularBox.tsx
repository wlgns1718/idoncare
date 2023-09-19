import React from "react";
import { Link } from "react-router-dom";
import SmallBtn from "./SmallBtn";

type RegularMoneyBoxProps = {
  regularDate: string;
  amount: string;
  startDate: string;
};

const RegularMoneyBox: React.FC<RegularMoneyBoxProps> = ({ regularDate, amount, startDate }) => {
  return (
    <div className="bg-gray p-10 pr-6 rounded-xl mt-5 text-s">
      <p className="mb-2">{regularDate}</p>
      <div className="flex justify-between items-center mb-8">
        <span className="text-main text-l font-strong">{amount}</span>
        <SmallBtn link="/" text="미입금 내역" bgColor="bg-light" textColor="text-main" />
      </div>
      <div className="flex justify-between items-center">
        <span>시작일: {startDate}</span>
        <Link to="/">
          <img src="/icons/icon-more.png" alt="Icon" className="w-6 h-6"/>
        </Link>
      </div>
    </div>
  );
};

export default RegularMoneyBox;
