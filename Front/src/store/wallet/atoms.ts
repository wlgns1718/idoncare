import { atom } from "recoil";
import KBICON from "../../assets/imgs/bank/PNG_KB.png";
import { AccountDataType, BankDataType, TradeItem } from "../../types/WalletTypes";
import { MonthlyTradeListResponse } from "../../components/wallet/TradeHistory";

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

export const resistRechargeAccountInput = atom<number | undefined>({
  key: "ResistRechargeAccountInput",
  default: undefined,
});

export const sendAccountBank = atom<BankDataType | null>({
  key: "sendAccountBank",
  default: null,
});

export const rechargeAccount = atom<AccountDataType | null>({
  key: "rechargeAccount",
  default: null,
});


export const tradeList = atom<TradeItem[]>({
  key: "tradeList",
  default: [],
});

export const searchResultTradeList = atom<MonthlyTradeListResponse[]>({
  key: "searchResultTradeList",
  default: [],
});