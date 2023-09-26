import { atom } from "recoil";

interface AuthenticationData {
  phoneNumber: number;
  birth: number;
  mobileSort: "SK" | "KT" | "LG";
  name: string;
}

export const authenticationData = atom<AuthenticationData>({
  key: "authenticationData",
  default: {
    phoneNumber: 0,
    birth: 0,
    mobileSort: "SK",
    name: "",
  },
});
