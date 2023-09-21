import { selector } from "recoil";
import { bankData } from "./atoms";

export const firstBank = selector({
  key: "firstBank",
  get: ({ get }) => {
    const first = get(bankData);

    return first;
  },
});
