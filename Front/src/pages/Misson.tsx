import Header from "../components/common/Header";
import MissionHistoryCard from "./../components/mission/MissionHistoryCard";

export type MissionData = {
  id: number;
  title: string;
  description: string;
  startDate: string;
  endDate: string;
  status: string;
  missionMoney: number;
};

const missions : MissionData[] = [
  {
    id: 1,
    title: "Mission Title 1",
    description: "Mission Description 1",
    startDate: "2021-01-01",
    endDate: "2021-01-01",
    status: "Active",
    missionMoney: 10000
  },
  {
    id: 2,
    title: "Mission Title 2",
    description: "Mission Description 2",
    startDate: "2021-01-01",
    endDate: "2021-01-01",
    status: "Active",
    missionMoney: 10000
  },
  {
    id: 3,
    title: "Mission Title 3",
    description: "Mission Description 3",
    startDate: "2021-01-01",
    endDate: "2021-01-01",
    status: "Active",
    missionMoney: 10000
  }
];

function Misson() {
  return (
    <div>
      <Header pageTitle="미션관리" headerLink="/home" />
      <div>자녀 선택 컴포넌트</div>
      <div>
        <div className="flex">
          <div>미션완료 아이콘</div>
          <div>{"지난 미션 >"}</div>
        </div>
        <div className="grid grid-cols-2">
          {
            missions.map((mission) => {
              return <MissionHistoryCard key={mission.id} mission={mission} />;
            })
          }
        </div>
      </div>
    </div>
  );
}

export default Misson;
