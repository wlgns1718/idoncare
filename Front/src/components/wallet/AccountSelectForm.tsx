// import { useState } from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import { BankDataType } from "../../types/WalletTypes";
import { sendAccountBank } from "../../store/wallet/atoms";
import { BottomSheet } from "../common/BottomSheet";
import BankGridList from "./BankGridList";
import { BottomSheetOpen } from "../../store/common/atoms";

function AccountSelectForm() {
  const selectedBank = useRecoilValue<BankDataType>(sendAccountBank);

  const [bottomSheetOpen, setBottomSheetOpen] = useRecoilState(BottomSheetOpen);

  const openSheet = () => setBottomSheetOpen(true);
  const closeSheet = () => setBottomSheetOpen(false);

  return (
    <div>
      <div
        className="w-full bg-gray flex p-3 mt-10 rounded-lg text-m text-mediumgray "
        onClick={openSheet}
      >
        {selectedBank?.name ? selectedBank?.name : "은행 선택"}
      </div>

      {bottomSheetOpen && (
        <BottomSheet size={75} closeSheet={closeSheet}>
          <BankGridList />
        </BottomSheet>
      )}

      <input
        type="number"
        name="account"
        id="account"
        autoComplete="account"
        className="w-full flex-1 border-0 my-6 bg-gray p-3 rounded-lg text-m outline-0 outline outline-main outline-offset-0 focus:outline-2 placeholder:text-gray-400 focus:ring text-main"
        placeholder="계좌 번호를 입력해주세요"
      />
      <div className="flex justify-end">
        <div className="bg-light p-3 rounded-lg ">계좌 확인</div>
      </div>
    </div>
  );
}

export default AccountSelectForm;
