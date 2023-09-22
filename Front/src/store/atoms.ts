import { atom } from "recoil";
import { BankDataType } from "../components/wallet/BankItem";

interface BankDataInterface { 
  bankList: BankDataType[];
}

export const bankData = atom<BankDataInterface>({
  key: "bankData",
  default: {
    bankList : [],
  },
});
