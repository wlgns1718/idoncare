import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Header from "../components/common/Header";
import KidDemandedList from "../components/pocketmoney/KidDemandedList";
import SmallBtn from "../components/pocketmoney/SmallBtn";
import RegularMoneyBox from "../components/pocketmoney/RegularBox";
import RegularMoneyBoxEmpty from "../components/pocketmoney/RegularBoxEmpty";
import SendMoneyBox from "../components/pocketmoney/MenuBox";

import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/selectors";
import AxiosHeader from "../apis/axios/AxiosHeader";
import { baseUrl } from "../apis/url/baseUrl";

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
  createdAt: string;
};

const PocketMoney: React.FC = () => {
  const token = useRecoilValue(userToken);

  const [kidDemandedList, setKidDemandedList] = useState<KidDemandedData[]>([]);
  const [regularPocketMoneyList, setRegularPocketMoneyList] = useState<
    RegularMoneyData[]
  >([]);

  const getRegularDate = (type: string, cycle: number) => {
    const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
  
    switch (type) {
      case "MONTH":
        return `매월 ${cycle}일`;
      case "WEEK":
        return `매주 ${daysOfWeek[cycle % 7]}요일`;
      case "DAY":
        return "매일";
      default:
        return "";
    }
  }

  const getStartDate = (createdAt: string) => {
    const date = new Date(createdAt);
    date.setDate(date.getDate() + 1);
    
    return date.toLocaleDateString();
  }

  useEffect(() => {
    axios
      .get(
        baseUrl + `api/pocketmoney/request?pageNum=1`,
        AxiosHeader({ token })
      )
      .then((response) => {
        const requestList = response.data.data.list.filter(
          (item: KidDemandedData) => item.type === "REQUEST"
        );
        setKidDemandedList(requestList);
      })
      .catch((error) => console.error("Error:", error));

    axios
      .get(baseUrl + `api/pocketmoney/regular`, AxiosHeader({ token }))
      .then((response) => {
        setRegularPocketMoneyList(response.data.data);
      })
      .catch((error) => console.error("Error:", error));
  }, [token]);

  return (
    <div className="pb-60">
      <div className="bg-green-100 text-m">
        <Link to="/kidDemandMoney">KID: 용돈 조르기</Link>
        <br />
        <Link to="/kidDemandMoneyList">KID: 용돈 요청 조회</Link>
      </div>

      <Header pageTitle="용돈" headerType="normal" headerLink="/" />

      <div className="m-10">
        <div className="pt-3 pb-3 mb-5 text-m font-strong">
          자녀가 요청한 용돈
        </div>

        <div className="ml-4">
          {kidDemandedList.map((demand) => (
            <KidDemandedList
              key={demand.pocketMoneyRequestId}
              name={demand.child.name}
              amount={demand.amount.toString()}
              pocketMoneyRequestId={demand.pocketMoneyRequestId}
            />
          ))}
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
        {regularPocketMoneyList.length > 0 ? (
    regularPocketMoneyList.map((money) => (
      <RegularMoneyBox
        regularPocketMoneyId={money.regularPocketMoneyId}
        regularDate={getRegularDate(money.type, money.cycle)}
        cname={money.childName}
        amount={`${money.amount.toLocaleString()}원`}
        startDate={getStartDate(money.createdAt)}
      />
    ))
  ) : (
     <RegularMoneyBoxEmpty />
   )}
      </div>
    </div>
  );
};

export default PocketMoney;
