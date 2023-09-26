import { selector } from "recoil";
import { userData } from "./atoms";

export const isLogin = selector({
  key: "isLogin",
  get: ({ get }) => get(userData).accessToken!== null,
})