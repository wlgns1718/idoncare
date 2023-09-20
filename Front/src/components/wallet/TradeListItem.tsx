import React from "react";
import Icon from "../common/Icon";

function TradeListItem() {
  return (
    <div className="flex m-5">
      <div className="flex-none bg-gray w-[40px] h-[40px] p-3 rounded-[50%]">
        <Icon size="small" name="purchase" />
      </div>
      <div className="flex w-full justify-between">
        <div className="ml-10">
          <div>이우철 용돈</div>
          <div>11:20</div>
        </div>
        <div>
          <div className="text-main text-left">-2,500 원</div>
          <div className="text-darkgray text-left">남은 돈 15,000 원</div>
        </div>
      </div>
    </div>
  );
}

export default TradeListItem;
