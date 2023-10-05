import { atom } from "recoil";

export interface MissionData {
  title: string;
  amount: number;
  type: 'UNPAID' | 'PROCESS' | 'COMPLETE';
  childId: number[];
  beforeMessage: string;
  afterMessage: string;
}

export const createMissionData = atom<MissionData>({
  key: "createMissionData",
  default: {
    title: "",
    type: "PROCESS",
    amount: 0,
    childId: [],
    beforeMessage: "",
    afterMessage: "",
  },
});


export const selectedChildId = atom<number | null>({
  key: 'selectedChildId',
  default: 0,
});
