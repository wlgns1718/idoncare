import { TradeCategory, TradeHistoryCategory } from "./WalletTypes";

export interface ChipProps {
  isSelected: boolean;
  category: TradeHistoryCategory;
  handler: (text: TradeCategory) => void;
}
