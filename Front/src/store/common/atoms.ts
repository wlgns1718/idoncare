import { atom } from "recoil";
import { UserData } from "../../types/UserData";

export const BottomSheetOpen = atom<boolean>({
  key: "BottomSheetOpen",
  default: false,
});

export const userToken = atom<string>({
  key: "UserToken",
  default:
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU3NzMxNzIsImV4cCI6MTY5NTgxNjM3Mn0.qPleDWrL5fyUs69LBGl4LbIYaWF9WI18ToyeAFsGhp0",
});


export const userData = atom<UserData | null>({
  key: "userData",
  default: {
    nickname: 'null',
    joined: true,
    userId: 12,
    email: 'null',
    refreshToken: 'null',
    accessToken:
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU3NzMxNzIsImV4cCI6MTY5NTgxNjM3Mn0.qPleDWrL5fyUs69LBGl4LbIYaWF9WI18ToyeAFsGhp0",
    role: "PARENT",
  },
});
