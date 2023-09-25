import { atom } from "recoil";

export const BottomSheetOpen = atom<boolean>({
  key: "BottomSheetOpen",
  default: false,
});

export const userToken = atom<string>({
  key: "UserToken",
  default:
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU2MTc0MDgsImV4cCI6MTY5NTY2MDYwOH0.qk-WbXkSahd2qDSuSo_P2em67rXDiLS8IiCFz2u2oKA",
});



export type UserData = {
  nickname: string | null;
  joined: boolean | null;
  userId: number | null;
  email: string | null;
  refreshToken: string | null;
  accessToken: string | null;
  role: "CHILD" | "PARENT" | null;
};

export const userData = atom<UserData>({
  key: "userData",
  default: {
    nickname: null,
    joined: null,
    userId: null,
    email: null,
    refreshToken: null,
    accessToken:
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU2MTc0MDgsImV4cCI6MTY5NTY2MDYwOH0.qk-WbXkSahd2qDSuSo_P2em67rXDiLS8IiCFz2u2oKA",
    role: null,
  },
});