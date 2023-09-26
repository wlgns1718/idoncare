export type MissionStateType = "REQUEST" | "PROCESS" | "UNPAID" | "COMPLETE";

export enum MissionStateName {
  REQUEST = "요청중",
  PROCESS = "진행중",
  UNPAID = "미지급",
  COMPLETE = "완료됨",
}

export type MissionDataType = {
  missionId: number;
  title: string;
  amount: number;
  type: MissionStateType;
};

export type MissionList = MissionDataType[];

export type MissionResistType = {
  parentId: number;
  childId: number[];
  title: string;
  amount: number;
  type: "REQUEST";
  beforeMessage: string;
  afterMessage: string;
};
