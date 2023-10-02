import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Header from "../components/common/Header";
import KidDemandedList from "../components/pocketmoney/KidDemandedList";
import SmallBtn from "../components/pocketmoney/SmallBtn";
import RegularMoneyBox from "../components/pocketmoney/RegularBox";
import RegularMoneyBoxEmpty from "../components/pocketmoney/RegularBoxEmpty";
import SendMoneyBox from "../components/pocketmoney/MenuBox";

type KidDemandedData = {
  pocketMoneyRequestId: number;
  parent: {
    id: number;
    name: string;
  };
  child: {
    id: number;
    name: string;
  };
  amount: number;
  type: string;
};

type RegularMoneyData = {
  regularPocketMoneyId: number;
  childUserId: number;
  childName: string;
  type: string;
  cycle: number;
  amount: number;
  createdAt: string; // 날짜는 보통 문자열로 받아옵니다.
};

const PocketMoney: React.FC = () => {
  const [kidDemandedList, setKidDemandedList] = useState<KidDemandedData[]>([]);
  const [regularPocketMoneyList, setRegularPocketMoneyList] = useState<RegularMoneyData[]>([]);

  useEffect(() => {
    
    fetch("http://j9d209.p.ssafy.io:8081/api/pocketmoney/request?pageNum=1", {
      method:'GET',
      headers:{
        'Content-Type': 'application/json',
        Authorization:
        // 부모
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE3MDk5MzM4OTgxNzIwMjAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYyMjEyMTIsImV4cCI6MTY5NjI2NDQxMn0.tdQ_hGCsmNw45LwAJTHGzodkW_BFLiVz9PZc-QUXXjQ"
      }
    })
    .then((response) => response.json())
    .then((result) => {
      const requestList = result.data.list.filter((item : KidDemandedData) => item.type === 'REQUEST');
      
    setKidDemandedList(requestList);
    })
    .catch((error) => console.error('Error:', error));
    
      
    fetch("http://j9d209.p.ssafy.io:8081/api/pocketmoney/regular", {
      method:'GET',
      headers:{
        'Content-Type': 'application/json',
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE3MDk5MzM4OTgxNzIwMjAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYyMjEyMTIsImV4cCI6MTY5NjI2NDQxMn0.tdQ_hGCsmNw45LwAJTHGzodkW_BFLiVz9PZc-QUXXjQ"
      }
    })
    .then((response) => response.json())
    .then((result) => {
      setRegularPocketMoneyList(result.data);
    })
    .catch((error) => console.error('Error:', error));
      
   }, []);

  return (
    <div className="pb-60">
      <div className="bg-green-100 text-m">
        <Link to="/kidDemandMoney">KID) 용돈 조르기</Link>
        <br />
        <Link to="/kidDemandMoneyList">KID) 용돈 요청 조회</Link>
      </div>

      <Header pageTitle="용돈" headerType="normal" headerLink="/" />

      <div className="m-10">
        <div className="pt-3 pb-3 mb-5 text-m font-strong">
          자녀가 요청한 용돈
        </div>

        <div className="ml-4">
          {kidDemandedList.map(demand =>
            <KidDemandedList 
              key={demand.pocketMoneyRequestId} 
              name={demand.child.name} 
              amount={demand.amount.toString()} 
              pocketMoneyRequestId={demand.pocketMoneyRequestId}
            />
          )}
          </div>

        <div className="text-right">
          <SmallBtn link="/" text="전체보기" classes="mb-10" />
        </div>

        <div className="flex justify-between">
          <SendMoneyBox
            link="/sendPocketMoney"
            bgColor="bg-yellow"
            textColor="text-white"
            text="용돈보내기"
            classes="mr-4"
          />
          <SendMoneyBox
            link="/sendRegularMoney"
            bgColor="bg-sky"
            textColor="text-white"
            text={
              <>
                <p>정기용돈으로</p>
                <p>편하게 용돈 주기</p>
              </>
            }
          />
        </div>

        <div className="text-m mt-14 font-strong">정기 용돈 목록</div>
        {regularPocketMoneyList.length > 0 ? regularPocketMoneyList.map(money =>
          <RegularMoneyBox
            regularPocketMoneyId={money.regularPocketMoneyId}
            regularDate={`매월 ${money.cycle}일`}
            cname={money.childName}
            amount={`${money.amount.toLocaleString()}원`}
            startDate={new Date(money.createdAt).toLocaleDateString()}
          />
        ) : <RegularMoneyBoxEmpty />}
      </div>
    </div>
  );
};

export default PocketMoney;
