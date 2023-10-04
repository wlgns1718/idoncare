/* eslint-disable react-hooks/rules-of-hooks */
/* eslint-disable @typescript-eslint/no-explicit-any */
import Header from "../components/common/Header";
import { useState } from "react";
import { BarDatum, ResponsiveBar } from "@nivo/bar";
import useComma from "../hooks/useComma";
import axios from "axios";
import { baseUrl } from "../apis/url/baseUrl";
import Kids, { RelationType } from "../components/active/Kids";
import AxiosHeader from "../apis/axios/AxiosHeader";
import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/selectors";

type DataScale = "daily" | "monthly";

interface APIResult<T> {
  code: number;
  error: string;
  data: T;
}

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

function Report() {
  const token = useRecoilValue(userToken);

  const [dateScale, setDateScale] = useState<DataScale>("daily");
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const [dailyData, setDailyData] = useState<Array<ChartData>>([]);
  const [monthlyData, setMonthlyData] = useState<Array<ChartData>>([]);

  const onClickChild = (child: RelationType) => {
    //해당 자녀를 선택하면 어떠한 행동을 할지 정의
    // -> 해당 자녀에 대한 일별, 월별 보고서 데이터 불러오기

    //일별 데이터 받아오기
    (async () => {
      const result = await axios.get<APIResult<Array<ChartData>>>(
        `${baseUrl}api/virtual/active/${child.userId}/daily`,
        AxiosHeader({ token })
      );

      const { code, data } = result.data;
      if (code === 200) {
        //성공적으로 불러 왔으면
        setDailyData(data);
      }
    })();

    //월별 데이터 받아오기
    (async () => {
      const result = await axios.get<APIResult<any>>(
        `${baseUrl}api/virtual/active/${child.userId}/monthly`,
        AxiosHeader({ token })
      );

      const { code, data } = result.data;
      if (code === 200) {
        //성공적으로 불러 왔으면
        setMonthlyData(data.list);
      }
    })();
  };

  return (
    <div>
      <Header pageTitle="활동보고서" />
      <Kids onClick={onClickChild} />
      <div>
        <div className="flex p-2 justify-between">
          <div className="flex">
            <div className="flex items-center">
              <div className="bg-blue-600 w-4 h-4"></div>수입
            </div>
            <div className="flex items-center ml-2">
              <div className="bg-red-600 w-4 h-4"></div>지출
            </div>
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
        <div className="w-full h-[40vh]">
          {}
          {dailyData.length !== 0 &&
            dateScale === "daily" &&
            BarData(dailyData)}
          {monthlyData.length !== 0 &&
            dateScale === "monthly" &&
            BarData(monthlyData)}
        </div>
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
      colors={["rgb(37, 99, 235)", "rgb(220, 38, 38)"]}
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
      innerPadding={5}
      padding={0.3}
      isInteractive={false}
      enableLabel={false}
      enableGridY={false}
    />
  );
};

export default Report;
