import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
// import SmallBtn from "./SmallBtn";
import BottomModal from "../common/Modal";
import YesNoBtn from "../common/YesNoBtn";

type RegularMoneyBoxProps = {
  regularDate: string;
  cname: string;
  amount: string;
  startDate: string;
  regularPocketMoneyId: number;
};

const RegularMoneyBox: React.FC<RegularMoneyBoxProps> = ({
  regularDate,
  cname,
  amount,
  startDate,
  regularPocketMoneyId,
}) => {

  const navigate = useNavigate();

  const [isModalOpen, setIsModalOpen] = useState(false);
  
    const handleDeleteClick = () => {
        setIsModalOpen(true);
    };

    const handleCancelClick = () => {
        setIsModalOpen(false);
    };

    const handleConfirmClick = () => {
      fetch("http://j9d209.p.ssafy.io:8081/api/pocketmoney/regular", {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE3MDk5MzM4OTgxNzIwMjAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYyMjEyMTIsImV4cCI6MTY5NjI2NDQxMn0.tdQ_hGCsmNw45LwAJTHGzodkW_BFLiVz9PZc-QUXXjQ"
        },
        body: JSON.stringify({ regularPocketMoneyId })
      })
      .then(response => response.json())
      .then(() => {
        navigate("/");
      })      
      .catch(error => console.error('Error:', error));
  };
  

  return (
    <div className="bg-gray p-10 pr-6 rounded-xl mt-5 text-s">
      <p className="mb-2 text-center">{cname}</p>
      <p className="mb-2">{regularDate}</p>
      <div className="flex justify-between items-center mb-8">
        <span className="text-main text-l font-strong">{amount}</span>
        {/* <SmallBtn
          link="/"
          text="미입금 내역"
          bgColor="bg-light"
          textColor="text-main"
        /> */}
      </div>
      <div className="flex justify-between items-center">
        <span>시작일: {startDate}</span>
        <Link to="#" onClick={handleDeleteClick}>
            <img src="/icons/icon-x.png" alt="Icon" className="w-6 h-6" />
          </Link>

          {isModalOpen && (
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
