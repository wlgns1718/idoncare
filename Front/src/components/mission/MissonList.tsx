import { useRecoilState } from "recoil";
import {
  MissionDataType,
  MissionStateName,
  MissionStateType,
} from "../../types/MissionTypes";
import { BottomSheet } from "../common/BottomSheet";
import Icon, { ICON_NAME } from "../common/Icon";
import MissionHistoryCard from "./MissionHistoryCard";
import { BottomSheetOpen } from "../../store/common/atoms";
import MissionHistoryPlusCard from "./MissionHistoryPlusCard";
import { useState } from "react";

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

const missionStates: {
  icon: ICON_NAME;
  type: MissionStateType;
  text: MissionStateName;
}[] = [
  {
    icon: "home",
    type: "ALL",
    text: MissionStateName.ALL,
  },
  {
    icon: "home",
    type: "REQUEST",
    text: MissionStateName.REQUEST,
  },
  {
    icon: "home",
    type: "COMPLETE",
    text: MissionStateName.COMPLETE,
  },
  {
    icon: "home",
    type: "PROCESS",
    text: MissionStateName.PROCESS,
  },
  {
    icon: "home",
    type: "UNPAID",
    text: MissionStateName.UNPAID,
  },
];

function MissonList() {
  const [bottomSheetOpen, setBottomSheetOpen] = useRecoilState(BottomSheetOpen);
  const [missionFilter, setMissionFilter] = useState<MissionStateType>("ALL");

  return (
    <div className={`${bottomSheetOpen ? "scroll" : ""}`}>
      <div className="flex justify-between mx-4 ">
        <div className="flex text-m gap-2">
          <div className="content-center flex">
            {MissionStateName[missionFilter]}
          </div>
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
            (missionFilter == "ALL" || missionFilter == mission.type) && (
              <MissionHistoryCard key={mission.missionId} mission={mission} />
            )
          );
        })}
        <MissionHistoryPlusCard />
      </div>
      <BottomSheet size={60}>
        <div className="flex flex-col gap-6 justify-evenly text-s px-10">
          {missionStates.map((state) => {
            return (
              <div
                className="flex items-center gap-4"
                key={state.icon}
                onClick={() => {
                  setMissionFilter(state.type);
                  setBottomSheetOpen(false);
                }}
              >
                <Icon name={state.icon}></Icon>
                <div>{state.text}</div>
              </div>
            );
          })}
        </div>
      </BottomSheet>
    </div>
  );
}

export default MissonList;
