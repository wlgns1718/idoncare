export type TradeHistoryCategory = {
  type: string;
  text: string;
};

export type BankDataType = {
  icon: string;
  name: string;
  code: number;
} | null;

export type AccountDataType = {
  realAccountId: string;
  bankName: string;
  pinNumber: string;
  bankCode: number;
};

export type RechargeAccountResponse = {
  realAccountId: string;
  bankName: string;
  pinNumber: string;
  bankCode: number;
  accountNumber: number;
  userId: number;
};

export type CashFlow = "WITHDRAWAL" | "DEPOSIT" | "ALL";

export type TradeType =
  | "CHARGE"
  | "MISSION"
  | "POCKET"
  | "TRANSFER"
  | "RETURN"
  | "PAYMENT";

export interface TradeItem {
  amount: number;
  balance: number;
  cashFlow: CashFlow;
  content: string;
  dayOfWeek: number;
  localDate: string;
  localTime: string;
  type: TradeType;
  userId: number;
}
