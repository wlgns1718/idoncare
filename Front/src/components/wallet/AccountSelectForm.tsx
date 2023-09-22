import { useState } from "react";
import { useRecoilValue } from "recoil";
import { BankDataType } from "../../types/WalletTypes";
import { sendAccountBank } from "../../store/atoms";
import { BottomSheet } from "../common/BottomSheet";
import BankGridList from "./BankGridList";

function AccountSelectForm() {
  const [isSheetOpen, setIsSheetOpen] = useState(false);
  const selectedBank = useRecoilValue<BankDataType>(sendAccountBank);
  const openSheet = () => {
    setIsSheetOpen(true);
  };
  const closeSheet = () => {
    setIsSheetOpen(false);
  };
  return (
    <div>
      <div
        className="w-full bg-gray flex p-3 mt-10 rounded-lg text-m text-mediumgray "
        onClick={openSheet}
      >
        은행선택 {selectedBank?.name}
      </div>
      {isSheetOpen && (
        <BottomSheet size={75} closeSheet={closeSheet}>
          <BankGridList />
        </BottomSheet>
      )}

      <input
        type="number"
        name="account"
        id="account"
        autoComplete="account"
        className="w-full flex-1 border-0 my-6 bg-gray p-3 rounded-lg text-m placeholder:text-gray-400 focus:ring-0 sm:text-sm sm:leading-1"
        placeholder="계좌 번호를 입력해주세요"
      />
      <div className="flex justify-end">
        <div className="bg-light p-3 rounded-lg ">계좌 확인</div>
      </div>
    </div>
  );
}

export default AccountSelectForm;
