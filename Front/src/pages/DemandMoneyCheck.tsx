import React, { useState } from "react";
import Header from "../components/common/Header";
import YesNoBtn from "../components/common/YesNoBtn";
import SmallBtn from "../components/pocketmoney/SmallBtn";
import ChildReguestMoneyDetail from "../components/pocketmoney/KidDemandedDetail";
import MoneyDone from "../components/pocketmoney/Done";
import DemandCheckModal from "../components/pocketmoney/DemandCheckModal";

const ChildReguestMoney: React.FC = () => {
  const [isAccepted, setIsAccepted] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleAccept = () => {
    setIsAccepted(true);
  };

  const handleOpenModal = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  if (isAccepted) {
    return (
      <MoneyDone
        title="용돈 보내기 완료"
        content={
          <>
            <div className="text-m">김슬기</div>
            <div className="text-m text-main">3,000원</div>
          </>
        }
        ps="남은 잔액 102,000원"
      />
    );
  }

  return (
    <div className="flex flex-col min-h-screen pb-60">
      <Header pageTitle="용돈 요청 관리" headerType="normal" headerLink="/" />

      <div className="m-10 text-center flex flex-col flex-grow">
        <SmallBtn text="대기중" classes="mb-10 block mx-auto" />

        <ChildReguestMoneyDetail
          name="김슬기"
          message="요청 메세지 히얼"
          amount="13,500원"
          requestDate="2023.09.10"
          cancelDate="2023.09.12"
        />

        <YesNoBtn
          yesLink=""
          onYesClick={handleAccept}
          onNoClick={handleOpenModal}
        />

        {isModalOpen && <DemandCheckModal onClose={handleCloseModal} />}
      </div>
    </div>
  );
};

export default ChildReguestMoney;
