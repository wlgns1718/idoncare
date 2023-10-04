import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import SmallBtn from "./SmallBtn";
import FullBtn from "../common/FullBtn";
import BottomModal from "../common/Modal";
import YesNoBtn from "../common/YesNoBtn";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../../store/common/selectors";
import AxiosHeader from "../../apis/axios/AxiosHeader";
import { baseUrl } from "../../apis/url/baseUrl";

type RegularMoneyBoxProps = {
  regularDate: string;
  cname: string;
  amount: string;
  startDate: string;
  regularPocketMoneyId: number;
};

type RejectedData = {
  regularPocketMoneyRejectedId: number;
  amount: string;
  dueDate: string;
};

const RegularMoneyBox: React.FC<RegularMoneyBoxProps> = ({
  regularDate,
  cname,
  amount,
  startDate,
  regularPocketMoneyId,
}) => {
  const token = useRecoilValue(userToken);
  const navigate = useNavigate();

  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
  const [isUnpaidHistoryModalOpen, setIsUnpaidHistoryModalOpen] = useState(false); 
  const [rejectedData, setRejectedData] = useState<RejectedData[]>([]);

  useEffect(() => {
    axios
      .get(baseUrl + "api/pocketmoney/regular/rejected", AxiosHeader({ token }))
      .then((response) => {
        if (response.data && Array.isArray(response.data)) {
          setRejectedData(response.data);
        } else {
          console.error("Unexpected response:", response);
        }
      })
      .catch((error) => console.error("Error:", error));
   }, [token]);

  const handleDeleteClick = () => {
    setIsDeleteModalOpen(true);
  };


  const handleCancelClick = () => {
    setIsDeleteModalOpen(false);
    setIsUnpaidHistoryModalOpen(false);
  };

  const handleConfirmClick = () => {
    axios
      .delete(baseUrl + "api/pocketmoney/regular", {
        ...AxiosHeader({ token }),
        data: { regularPocketMoneyId },
      })
      .then(() => {
        navigate("/");
      })
      .catch((error) => console.error("Error:", error));
  };
  
  const handleShowUnpaidHistoryClick = () => {
    setIsUnpaidHistoryModalOpen(true);
   };

  return (
    <div className="bg-gray p-10 pr-6 rounded-xl mt-5 text-s">
      <p className="mb-2 text-center">{cname}</p>
      <p className="mb-2">{regularDate}</p>
      <div className="flex justify-between items-center mb-8">
        <span className="text-main text-l font-strong">{amount}</span>
        <SmallBtn
          text="미입금 내역"
          bgColor="bg-light"
          textColor="text-main"
          onClick={handleShowUnpaidHistoryClick}
        />
      </div>

      {isUnpaidHistoryModalOpen && (
        <BottomModal>
          <div className="mt-10 mb-10">
            {rejectedData.length === 0 ? (
              <p className="text-center mb-12">미입금 내역이 없습니다.</p>
            ) : (
              rejectedData.map((data) => (
                <div key={data.regularPocketMoneyRejectedId}>
                  <p>{data.amount}원</p>
                  <p>{data.dueDate}</p>
                </div>
              ))
            )}
            <FullBtn buttonText="확인" onClick={handleCancelClick} />
          </div>
        </BottomModal>
      )}

      <div className="flex justify-between items-center">
        <span>시작일: {startDate}</span>
        <Link to="#" onClick={handleDeleteClick}>
          <img src="/icons/icon-x.png" alt="Icon" className="w-6 h-6" />
        </Link>

        {isDeleteModalOpen && (
         <BottomModal>
            <div className="mt-10 mb-10">
              <p className="text-center">정기용돈을 해제할까요?</p>
              <YesNoBtn
                noText="아니오"
                yesText="네"
                onNoClick={handleCancelClick}
                onYesClick={handleConfirmClick}
              />
            </div>
          </BottomModal>
        )}
      </div>
    </div>
  );
};

export default RegularMoneyBox;
