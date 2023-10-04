/* eslint-disable react-hooks/rules-of-hooks */
/* eslint-disable @typescript-eslint/no-explicit-any */
import Header from "../components/common/Header";
import { useEffect, useState } from "react";
import { BarDatum, ResponsiveBar } from "@nivo/bar";
import useComma from "../hooks/useComma";
import axios from "axios";
import { baseUrl } from "../apis/url/baseUrl";
import Kids from "../components/active/Kids";

type DataScale = "daily" | "monthly";

interface DateOptions {
  label: string;
  value: DataScale;
}

const dateOptions: DateOptions[] = [
  { value: "daily", label: "일" },
  { value: "monthly", label: "월" },
];

interface ChartData extends BarDatum {
  day: string;
  earn: number;
  expend: number;
}

const dummyData: Array<ChartData> = [
  { day: "10.01", earn: 1000, expend: 10000 },
  { day: "10.02", earn: 2000, expend: 5000 },
  { day: "10.03", earn: 500, expend: 3000 },
  { day: "10.04", earn: 1500, expend: 2000 },
];

const SummaryItem = () => {
  return (
    <div className="w-full flex p-6 rounded-lg shadow-lg bg-gray justify-between">
      <div>아이콘</div>
      <div className="flex-grow mx-4 text-center">
        지난주보다 1000만원 더 썼어요.
      </div>
    </div>
  );
};

function Report() {
  const [dateScale, setDateScale] = useState<DataScale>("daily");
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const [dailyData, setDailyData] = useState<Array<ChartData>>(dummyData);

  // 초기 데이터 불러오기
  useEffect(() => {
    (async () => {
      const { status, data } = await axios.get(baseUrl + "api/");
      if (status !== 200) {
        //요청자체가 제대로 처리 되지 않았을때
        return;
      }
    })();
  }, []);

  return (
    <div>
      <Header pageTitle="활동보고서" />
      <Kids />
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
        {/* <div className="mx-2">
          <span>기간 시작</span> ~ <span>기간 끝</span>
        </div> */}
        <div className="w-full h-[40vh]">{BarData(dailyData)}</div>
        dd
        {/* <div className="flex">
          <SummaryItem />
        </div> */}
      </div>
    </div>
  );
}

const BarData = (data: Array<ChartData>) => {
  return (
    <ResponsiveBar
      data={data}
      indexBy="day"
      keys={["earn", "expend"]}
      groupMode="grouped"
      margin={{ top: 10, bottom: 50, left: 60, right: 10 }}
      enableLabel={false}
      colors={["rgb(0, 128, 255)", "rgb(255, 74, 74)"]}
      enableGridY={false}
      axisLeft={{
        tickSize: 0,
        tickValues: 5,
        format: (num) => useComma(num),
      }}
      axisBottom={{
        tickSize: 2,
        tickPadding: 5,
        legendPosition: "middle",
        legendOffset: 32,
      }}
      isInteractive={false}
    />
  );
};

export default Report;
