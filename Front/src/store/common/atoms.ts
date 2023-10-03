import { atom } from "recoil";
import { UserData } from "../../types/UserData";

export const BottomSheetOpen = atom<boolean>({
  key: "BottomSheetOpen",
  default: false,
});

export const userToken = atom<string>({
  key: "UserToken",
  default:
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQ2MTU2OTczMzI3ODkzMzAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYzMjA5MjIsImV4cCI6MTY5NjM2NDEyMn0.DbcpMjz5boirYukJqXBu6yWn_tegFE4mFQMIm7BC6Rk",
});

export const userData = atom<UserData | null>({
  key: "userData",
  default: {
    nickname: "null",
    joined: true,
    userId: 12,
    email: "null",
    refreshToken: "null",
    accessToken:
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQ2MTU2OTczMzI3ODkzMzAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYzMjA5MjIsImV4cCI6MTY5NjM2NDEyMn0.DbcpMjz5boirYukJqXBu6yWn_tegFE4mFQMIm7BC6Rk",
    role: "PARENT",
  },
});
