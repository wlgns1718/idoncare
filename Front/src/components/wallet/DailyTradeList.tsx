import { TradeItem } from '../../types/WalletTypes';
import TradeListItem from './TradeListItem';

interface DailyTradeListProps {
  tradeList: Array<TradeItem>;
}


function DailyTradeList({ tradeList }: DailyTradeListProps) {
  if(tradeList.length === 0) {
    return (
      <div className='p-10 text-m text-center text-main'>거래 내역이 없습니다..</div>
    )
  }
  return (
    <div className="">
      <div className="px-4 my-4">
        <div>9월 7일</div>
        <hr />
      </div>
      {/* 항목 */}
      <div className="">
        {tradeList?.map((item, index) => (
          <TradeListItem key={index} item={item} />
        ))}
      </div>
    </div>
  );
}

export default DailyTradeList