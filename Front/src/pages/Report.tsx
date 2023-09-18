import { useState } from "react";
import Header from "../components/common/Header";

function Report() {
  // const [dateScale, setDateScale] = useState(new Date("weekly"));

  return (
    <div>
      <Header pageTitle="활동보고서" />
      <div>
        <div className="p-2 flex">
          <div className="flex flex-col">
            <div>7일간의 보고서 아이콘</div>
            <div>기간시작 ~ 기간끝</div>
          </div>
          <div className="flex bg-main rounded-3xl px-2 py-1">
            <div className="py-1 px-4 bg-white rounded-3xl text-s" onClick={}>주</div>
            <div className="py-1 px-4 bg-main rounded-3xl text-s">월</div>
          </div>
        </div>
        <div className="h-30 w-full">그래프 공간</div>
        <div>
          <div className="flex bg-gray shadow-lg rounded-lg p-6">
            <div>아이콘</div>
            <div>지난주보다 1000만원 더 썼어요.</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Report;
