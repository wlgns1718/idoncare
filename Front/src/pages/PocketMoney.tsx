import React from "react";
import { Link } from "react-router-dom";
import Header from "../components/common/Header";
import ChildReguestMoneyList from "../components/pocketmoney/KidDemandedList";
import SmallBtn from "../components/pocketmoney/SmallBtn";
import RegularMoneyBox from "../components/pocketmoney/RegularBox";
import RegularMoneyBoxEmpty from "../components/pocketmoney/RegularBoxEmpty";
import SendMoneyBox from "../components/pocketmoney/MenuBox";

const PocketMoney: React.FC = () => {
  return (
    <div className="pb-60">

      <div className="bg-green-100 text-m">
        <Link to="/kidDemandMoney">KID) 용돈 조르기</Link><br/>
        <Link to="/kidDemandMoneyList">KID) 용돈 요청 조회</Link>
      </div>

      <Header pageTitle="용돈" headerType="normal" headerLink="/" />

      <div className="m-10">

        <div className="pt-3 pb-3 mb-5 text-m font-strong">자녀가 요청한 용돈</div>
          
        <div className="ml-4">
          <ChildReguestMoneyList name="김슬기" amount="30,000"/>
          <ChildReguestMoneyList name="이우철" amount="20,000"/>
        </div>

        <div className="text-right">
          <SmallBtn link="/" text="전체보기" classes="mb-10"/>
        </div>

        <div className="flex justify-between">
          <SendMoneyBox link="/sendPocketMoney" bgColor="bg-yellow" textColor="text-white" text="용돈보내기" classes="mr-4"/>
          <SendMoneyBox link="/sendRegularMoney" bgColor="bg-sky" textColor="text-white" text={<><p>정기용돈으로</p><p>편하게 용돈 주기</p></>} />
        </div>

        <div className="text-m mt-14 font-strong">정기 용돈 목록</div>
          <RegularMoneyBox regularDate='매월 2일' amount='15,000원' startDate='2023.09.13'/>
          <RegularMoneyBoxEmpty />
        </div>

    </div>
  );
};

export default PocketMoney;
