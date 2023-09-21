import TradeListItem from './TradeListItem';

function DailyTradeList() {
  return (
    <div className="">
      <div className="px-4 my-4">
        <div>9월 7일</div>
        <hr />
      </div>
      {/* 항목 */}
      <div className="">
        <TradeListItem />
        <TradeListItem />
        <TradeListItem />
      </div>
    </div>
  );
}

export default DailyTradeList