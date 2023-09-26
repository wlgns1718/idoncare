import { TradeItem } from "../../types/WalletTypes";
import Icon from "../common/Icon";

interface TradeItemProps {
  item: TradeItem;
}

function TradeListItem({item}: TradeItemProps) {
  return (
    <div className="flex m-5">
      <div className="flex-none bg-gray w-[40px] h-[40px] p-3 rounded-[50%]">
        <Icon size="small" name="purchase" />
      </div>
      <div className="flex w-full justify-between">
        <div className="ml-10">
          <div>{item.content}</div>
          <div>{item.localDate}</div>
        </div>
        <div>
          <div className="text-main text-left">-{item.amount} 원</div>
          <div className="text-darkgray text-left">남은 돈 {item.balance} 원</div>
        </div>
      </div>
    </div>
  );
}

export default TradeListItem;
