import { atom } from "recoil";
import KBICON from "../assets/imgs/bank/PNG_KB.png";
import { BankDataType } from "../types/WalletTypes";


interface BankDataInterface { 
  bankList: BankDataType[];
}

export const bankData = atom<BankDataInterface>({
  key: "bankData",
  default: {
    bankList: [
      {
        icon: KBICON,
        code: 1,
        name: "KB국민",
      },
      {
        icon: KBICON,
        code: 2,
        name: "KB국민",
      },
      {
        icon: KBICON,
        code: 3,
        name: "KB국민",
      },
      {
        icon: KBICON,
        code: 4,
        name: "KB국민",
      },
      {
        icon: KBICON,
        code: 5,
        name: "KB국민",
      },
      {
        icon: KBICON,
        code: 6,
        name: "KB국민",
      },
      {
        icon: KBICON,
        code: 5,
        name: "KB국민",
      },
    ],
  },
});

export const sendAccountBank = atom<BankDataType>({
  key: "sendAccountBank",
  default: {
    icon: KBICON,
    code: 1,
    name: "삼성",
  },
});
