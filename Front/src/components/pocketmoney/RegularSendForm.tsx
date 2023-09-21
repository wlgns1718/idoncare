import React, { useState } from "react";
import Header from "../common/Header";
// import NumberPlate from "../common/NumberPlate";
import FullBtn from "../common/FullBtn";
import Modal from "../common/Modal";
import RegularMoneySendFormCycle from "./RegularSendFormCycle";
import MoneyAmountSet from "../common/MoneyAmountSet";

interface Props {
  onNext: () => void;
}

const RegularSendForm: React.FC<Props> = ({ onNext }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  // const [moneyAmount, setMoneyAmount] = useState(0);
  const [tempTransferCycle, setTempTransferCycle] = useState<string | null>(
    null
  );

  // const amounts = [
  //   { value: 500, label: "5백" },
  //   { value: 1000, label: "1천" },
  //   { value: 10000, label: "1만" },
  // ];

  return (
    <div className="flex flex-col h-screen pb-60">
      <Header pageTitle="정기용돈 등록" headerType="normal" headerLink="/" />

      <div className="m-10 text-center flex-grow">
        <div className="text-l mt-12 mb-16">
          이우철
          <br />
          정기용돈 등록
        </div>

        <div
          onClick={() => setIsModalOpen(true)}
          className="bg-gray p-4 rounded-lg text-sm mb-10"
        >
          {tempTransferCycle ||
            "이체주기를 선택해주세요 (내일부터 시작됩니다) ▼"}
        </div>

        {isModalOpen && (
          <Modal>
            <div className="text-m font-strong mt-5">이체주기</div>
            <RegularMoneySendFormCycle
              onSelected={setTempTransferCycle}
              onClose={() => setIsModalOpen(false)}
            />
          </Modal>
        )}

        {/* <div className="text-l text-main font-strong">
          {moneyAmount
            .toString()
            .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")}{" "}
          원
        </div>
      </div>

      <div className="mt-auto text-center">
        <div className="flex justify-center gap-10">
          {amounts.map((item) => (
            <div
              key={item.label}
              onClick={() =>
                setMoneyAmount((prevValue) => prevValue + item.value)
              }
              className="px-5 py-3 bg-gray rounded-3xl"
            >
              +{item.label}
            </div>
          ))}
        </div>

        <NumberPlate bottomLeftText="00" /> */}

        <MoneyAmountSet />

        <FullBtn buttonText="다음" onClick={onNext} />
      </div>
    </div>
  );
};

export default RegularSendForm;
