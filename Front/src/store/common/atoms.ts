import { atom } from "recoil";
import { UserData } from "../../types/UserData";

export const BottomSheetOpen = atom<boolean>({
  key: "BottomSheetOpen",
  default: false,
});

export const userToken = atom<string>({
  key: "UserToken",
  default:
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU2ODg0OTcsImV4cCI6MTY5NTczMTY5N30.gDzzodoE7Ze64nEfnP72qpc84xeuj1U0qHrFscm5TH4",
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
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU2ODg0OTcsImV4cCI6MTY5NTczMTY5N30.gDzzodoE7Ze64nEfnP72qpc84xeuj1U0qHrFscm5TH4",
    role: "PARENT",
  },
});
