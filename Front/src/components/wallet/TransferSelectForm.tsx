import { useState } from "react";
import { useNavigate } from "react-router-dom";
import {BottomSheet} from "../common/BottomSheet";

function TransferSelectForm() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const navigate = useNavigate();

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <div>
      <div>
        <div className="">누구에게 보낼래요?</div>
        <div className="p-1 rounded-lg bg-gray flex w-auto">
          <div className="rounded-lg bg-white p-1">계좌</div>
          <div className="rounded-lg p-1">가족</div>
        </div>
      </div>

      <div>
        <div>자녀 1</div>
        <div>자녀 2</div>
      </div>

      <div >
        <div className="" onClick={openModal}>은행선택</div>
        {isModalOpen && <BottomSheet children={<div onClick={closeModal}>닫기</div>}/>}
        <form action="">
          <input type="number" placeholder="계좌번호 입력" />
        </form>
        <div className="bg-gray">계좌 확인</div>
        <div>등록된 계좌</div>
        <div
          onClick={() => {
            navigate("confirm");
          }}
        >
          농협 2348234982343
        </div>
      </div>
      <div>
        <div>자녀 1</div>
        <div>자녀 2</div>
      </div>
    </div>
  );
}

export default TransferSelectForm;
