import React from "react";
import { Link } from "react-router-dom";
import Header from "../components/common/Header";
import ChildReguestMoney from "../components/pocketmoney/ChildReguestMoney";

const PocketMoney: React.FC = () => {
  return (
    <div className="pb-60">

      <div className="bg-green-100">
        <Link to="/reguestedMoney">ReguestedMoney</Link>
      </div>

      <Header pageTitle="용돈" headerType="normal" headerLink="/" />

      <div className="m-10">

        <div className="pt-3 pb-3 mb-5 text-m font-strong">자녀가 요청한 용돈</div>
          
      {/* 용돈 요청 리스트 */}
        <div className="ml-4">

          <div className="flex justify-between items-center text-s mb-8">
            
            <ChildReguestMoney name="김슬기" amount="30,000"/>
            
            {/* <div className="flex items-center">
              <img src="/icons/icon-letter.png" alt="Icon" className="ml-2 mr-5 w-10 h-10"/>
              <Link to="/childReguestMoney">
                <div>이우철</div>
              </Link>
            </div>
            <div className="mr-3">3000원</div>
          </div> */}

          </div>
        </div>

          <div className="text-right">
            <Link to="/" className="bg-light p-3 pl-7 pr-7 rounded-3xl inline-block mt-4 mb-16 text-s text-main">
              전체보기
            </Link>
          </div>

          <div className="flex justify-between">
            <Link to="/sendPocketMoney" className="bg-yellow border-yellow box-content rounded-xl h-40 w-60 pt-9 pl-10 border-4 mr-10 text-sm text-white">
              용돈보내기</Link>
            <Link to="/sendRegularMoney" className="bg-sky border-sky box-content rounded-lg h-40 w-60 pt-9 pl-10 border-4 text-sm text-white">
              정기용돈으로<br/>편하게 용돈 주기</Link>
          </div>

          <div className="text-m mt-14 font-strong">정기 용돈 목록</div>

            <div className="bg-gray p-10 pr-6 rounded-xl mt-5 text-s">
              <p className="mb-2">매월 1일</p>
              <div className="flex justify-between items-center mb-8">
                <span className="text-main text-l font-strong">5000원</span>
                <Link to="/" className="text-main bg-light p-3 pl-7 pr-7 rounded-3xl">미입금 내역</Link>
              </div>
              <div className="flex justify-between items-center">
                <span>시작일: 2023.09.13</span>
                <Link to="/">
                  <img src="/icons/icon-more.png" alt="Icon" className="w-6 h-6"/>
                </Link>
              </div>
            </div>

        </div>

    </div>
  );
};

export default PocketMoney;
