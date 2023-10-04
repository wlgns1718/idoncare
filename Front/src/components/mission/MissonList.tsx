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
import React, { useEffect, useState } from "react";
import axios from "axios";

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

const MissonList: React.FC = () => {
  const [bottomSheetOpen, setBottomSheetOpen] = useRecoilState(BottomSheetOpen);
  const [missionFilter, setMissionFilter] = useState<MissionStateType>("ALL");
  const [missions, setMissions] = useState<MissionDataType[]>([]);

  useEffect(() => {
    axios
      .get("https://j9d209.p.ssafy.io:9081/api/mission", {
        headers: {
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjI3NTA4OTk0NDY4NDM5ODAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYzMjAzNzQsImV4cCI6MTY5NjM2MzU3NH0.6byHM3nkXrY3rU4VaEFxxVc-3eXPVYF_0ClmslKOdqA",
        },
      })
      .then((response) => {
        setMissions(response.data.data);
      });
  }, []);

  return (
    <div className={`${bottomSheetOpen ? "scroll" : ""}`}>
      <div className="flex justify-between mx-4 ">
        <div className="flex text-m gap-2">
          <div className="content-center flex mb-8">
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
      <BottomSheet>
        <div className="flex flex-col gap-6 justify-evenly text-s px-10">
          {missionStates.map((state) => {
            return (
              <div
                className="flex items-center gap-4"
                key={state.type}
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
};

export default MissonList;
