export type TradeCategory = "ALL" | "IN" | "OUT";

export type TradeHistoryCategory = {
  type: TradeCategory;
  text: string;
};

export type BankDataType = {
  icon: string;
  name: string;
  code: number;
} | null;