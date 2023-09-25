import { atom } from "recoil";
import KBICON from "../../assets/imgs/bank/PNG_KB.png";
import { AccountDataType, BankDataType } from "../../types/WalletTypes";

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

export const userBalanace = atom<number>({
  key: "userBalanace",
  default: 0,
});

export const sendAccountBank = atom<BankDataType>({
  key: "sendAccountBank",
  default: {
    icon: KBICON,
    code: 1,
    name: "삼성",
  },
});

export const rechargeAccount = atom<AccountDataType>({
  key: "rechargeAccount",
  default: {
    bankCode: 22,
    bankName: "NH농협",
    accountNumber: 132992140129,
  },
});
