import { atom } from "recoil";

export const BottomSheetOpen = atom<boolean>({
  key: "BottomSheetOpen",
  default: false,
});