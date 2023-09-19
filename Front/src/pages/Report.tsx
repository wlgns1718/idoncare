import Header from "../components/common/Header";
import { useState } from "react";

type DataScale = "weekly" | "monthly";

interface DateOptions {
  label: string;
  value: DataScale;
}

const dateOptions: DateOptions[] = [
  { value: "weekly", label: "주" },
  { value: "monthly", label: "월" },
];

const SummaryItem = () => {
  return (
    <div className="w-full flex p-6 rounded-lg shadow-lg bg-gray justify-between">
      <div>아이콘</div>
      <div className="flex-grow mx-4 text-center">지난주보다 1000만원 더 썼어요.</div>
    </div>
  );
}

function Report() {
  const [dateScale, setDateScale] = useState<DataScale>();

  return (
    <div>
      <Header pageTitle="활동보고서" />
      <div>
        <div className="flex p-2  justify-between">
          <div className="flex flex-col">
            <div className="text-m">7일간의 보고서 아이콘</div>
          </div>
          <div className="flex px-2 py-2 bg-main rounded-3xl items-center leading-1">
            {dateOptions.map((item, index) => {
              return (
                <div
                  className={`px-6 py-0 rounded-3xl text-s  ${
                    dateScale == item.value
                      ? "bg-white text-main"
                      : "bg-main text-white"
                  }`}
                  key={index}
                  onClick={() => setDateScale(item.value)}
                >
                  {item.label}
                </div>
              );
            })}
          </div>
        </div>
        <div className="mx-2">
          <span>기간 시작</span> ~ <span>기간 끝</span>
        </div>
        <div className="w-full h-[40vh]">그래프 공간</div>
        <div className="flex">
          <SummaryItem/>
        </div>
      </div>
    </div>
  );
}

export default Report;
