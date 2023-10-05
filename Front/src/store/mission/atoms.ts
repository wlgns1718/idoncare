import { atom } from "recoil";

export interface MissionData {
  missionId: number;
  parentId: number;
  // childId: number;
  childId: number[];
  parentName: string;
  childName: string;
  title: string;
  amount: number;
  type: "REQUEST" | "PROCESS" | "UNPAID" | "COMPLETE";
}


// export const createMissionData = atom<MissionData>({
//   key: "createMissionData",
//   default: {
//     title: "",
//     type: "PROCESS",
//     amount: 0,
//     childId: [],
//     beforeMessage: "",
//     afterMessage: "",
//   },
// });


export const selectedChildId = atom<number | null>({
  key: 'selectedChildId',
  default: 0,
});
