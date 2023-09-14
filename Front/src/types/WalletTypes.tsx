export type TradeCategory = "ALL" | "IN" | "OUT";

export type TradeHistoryCategory = {
  type: TradeCategory;
  text: string;
};
