import { TradeItem } from '../../types/WalletTypes';
import DailyTradeList from './DailyTradeList'

interface TotalTradeListProps {
  tradeList: Array<TradeItem>;
} 

function TotalTradeList({ tradeList }: TotalTradeListProps) {
  return <div>{<DailyTradeList tradeList={tradeList} />}</div>;
}

export default TotalTradeList