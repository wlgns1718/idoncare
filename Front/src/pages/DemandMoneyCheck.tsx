import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Header from "../components/common/Header";
import YesNoBtn from "../components/common/YesNoBtn";
import SmallBtn from "../components/pocketmoney/SmallBtn";
import ChildReguestMoneyDetail from "../components/pocketmoney/KidDemandedDetail";
import MoneyDone from "../components/pocketmoney/Done";
import DemandCheckModal from "../components/pocketmoney/DemandCheckModal";

type KidDemandedData = {
  pocketMoneyRequestId: number;
  parent: {
    id: number;
    name: string;
  };
  child: {
    id: number;
    name: string;
  };
  amount: number;
  type: string;
  createdAt: string;
  cancelDate: string;
};

const DemandMoneyCheck: React.FC = () => {
  const [requestData, setRequestData] = useState<KidDemandedData | null>(null);
  // const { pocketMoneyRequestId } = useParams<{ pocketMoneyRequestId: string }>();
  const [isAccepted, setIsAccepted] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const sendRequest = (type: string) => {
    fetch(`http://j9d209.p.ssafy.io:8081/api/pocketmoney/request`, {
      method:'PUT',
      headers:{
        'Content-Type': 'application/json',
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU3NzkxNTYsImV4cCI6MTY5NTgyMjM1Nn0.NOi0Wv2MSqFgcgeSvyElB1n07bLe9AUFl2R0Jy9jck4"
      },
      body: JSON.stringify({
        pocketMoneyRequestId: Number(pocketMoneyRequestId),
        type
      })
    })
    .then((response) => response.json())
    .then((result) => {
      console.log(result);
      
      if (result.code === 200 && type === 'ACCEPT') {
        setIsAccepted(true);
        setIsModalOpen(false);
      }
      
    })
    .catch((error) => console.error('Error:', error));
  };
  

  const handleAccept = () => {
    sendRequest('ACCEPT');
    setIsAccepted(true);
  };

  const handleOpenModal = () => {
    sendRequest('REJECT');
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const { pocketMoneyRequestId } = useParams<{
    pocketMoneyRequestId: string;
  }>();

  useEffect(() => {
    fetch(`http://j9d209.p.ssafy.io:8081/api/pocketmoney/request`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU3NzkxNTYsImV4cCI6MTY5NTgyMjM1Nn0.NOi0Wv2MSqFgcgeSvyElB1n07bLe9AUFl2R0Jy9jck4",
      },
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        setRequestData(result.data.list[0]);
      })
      .catch((error) => console.error("Error:", error));
  }, [pocketMoneyRequestId]);

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

        {requestData && (
          <ChildReguestMoneyDetail
            name={requestData.child.name}
            message={requestData.type}
            amount={requestData.amount.toString()}
            // amount={requestData.amount}
            requestDate={
              new Date(requestData.createdAt).toISOString().split("T")[0]
            }
            cancelDate={
              new Date(requestData.cancelDate).toISOString().split("T")[0]
            }
          />
        )}

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

export default DemandMoneyCheck;
