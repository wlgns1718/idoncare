import { selector } from "recoil";
import { userData } from "./atoms";

export const isLogin = selector({
  key: "isLogin",
  get: ({ get }) => get(userData)?.accessToken!== null,
})

export const userToken = selector({
  key: "UserToken",
  get: ({ get }) => get(userData).accessToken,
});