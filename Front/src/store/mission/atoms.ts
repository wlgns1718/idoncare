import { atom } from "recoil";

interface MissionData {
  title: string;
  amount: number;
  type: string;
  childId: number[];
  beforeMessage: string;
  afterMessage: string;
}

export const createMissionData = atom<MissionData>({
  key: "createMissionData",
  default: {
    title: "",
    type: "",
    amount: 0,
    childId: [],
    beforeMessage: "",
    afterMessage: "",
  },
});
