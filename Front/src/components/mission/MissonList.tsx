import { useSetRecoilState } from "recoil";
import { MissionDataType } from "../../types/MissionTypes";
import { BottomSheet } from "../common/BottomSheet";
import Icon from "../common/Icon";
import MissionHistoryCard from "./MissionHistoryCard";
import { BottomSheetOpen } from "../../store/common/atoms";
import MissionHistoryPlusCard from "./MissionHistoryPlusCard";

const missions: MissionDataType[] = [
  {
    missionId: 1,
    title: "Mission Title 1",
    type: "COMPLETE",
    amount: 10000,
  },
  {
    missionId: 2,
    title: "Mission Title 2",
    type: "PROCESS",
    amount: 10000,
  },
  {
    missionId: 3,
    title: "Mission Title 4",
    type: "REQUEST",
    amount: 10000,
  },
  {
    missionId: 4,
    title: "Mission Title 6",
    type: "UNPAID",
    amount: 10000,
  },
];

function MissonList() {
    const setBottomSheetOpen =
      useSetRecoilState(BottomSheetOpen);

  return (
    <div>
      <div className="flex justify-between mx-4 ">
        <div className="flex text-m gap-2">
          <div className="content-center flex">미션완료</div>
          <div
            onClick={() => {
              setBottomSheetOpen(true);
            }}
          >
            <Icon name="menu" className="mx-4" />
          </div>
        </div>
        <div className="flex text-t mt-2">
          <div>지난 미션</div>
          <Icon name="chevron-right" className="w-7 " />
        </div>
      </div>
      <div className="grid grid-cols-2 items-center px-4">
        {missions.map((mission) => {
          return (
            <MissionHistoryCard key={mission.missionId} mission={mission} />
          );
        })}
        <MissionHistoryPlusCard />
      </div>
      <BottomSheet>
        <div>미션 요청 대기중</div>
        <div>미션 진행중</div>
        <div>리워드 미지급</div>
        <div>완료한 미션</div>
      </BottomSheet>
    </div>
  );
}

export default MissonList;
