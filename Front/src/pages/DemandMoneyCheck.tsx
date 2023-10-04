import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Header from "../components/common/Header";
import YesNoBtn from "../components/common/YesNoBtn";
import SmallBtn from "../components/pocketmoney/SmallBtn";
import KidDemandedDetail from "../components/pocketmoney/KidDemandedDetail";
import MoneyDone from "../components/pocketmoney/Done";
import DemandCheckModal from "../components/pocketmoney/DemandCheckModal";
import { KidDemandedData } from "../components/pocketmoney/TypeDemand";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/selectors";
import AxiosHeader from "../apis/axios/AxiosHeader";
import { baseUrl } from "../apis/url/baseUrl";

const DemandMoneyCheck: React.FC = () => {
  const token = useRecoilValue(userToken);
  const [requestData, setRequestData] = useState<KidDemandedData | null>(null);
  // const { pocketMoneyRequestId } = useParams<{ pocketMoneyRequestId: string }>();
  const [isAccepted, setIsAccepted] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const sendRequest = (type: string) => {
    axios
      .put(
        baseUrl + `api/pocketmoney/request`,
        {
          pocketMoneyRequestId: Number(pocketMoneyRequestId),
          type,
        },
        AxiosHeader({ token })
      )
      .then((response) => {
        // console.log(response);

        if (response.data.code === 200 && type === "ACCEPT") {
          setIsAccepted(true);
          setIsModalOpen(false);
        }
      })
      .catch((error) => console.error("Error:", error));
  };

  const handleAccept = () => {
    sendRequest("ACCEPT");
    setIsAccepted(true);
  };

  const handleOpenModal = () => {
    sendRequest("REJECT");
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const { pocketMoneyRequestId } = useParams<{
    pocketMoneyRequestId: string;
  }>();

  useEffect(() => {
    axios
      .get(
        baseUrl + `api/pocketmoney/request?pageNum=1`,
        AxiosHeader({ token })
      )
      .then((response) => {
        console.log(response);
        setRequestData(
          response.data.data.list[Number(pocketMoneyRequestId) - 1]
        );
      })
      .catch((error) => console.error("Error:", error));
  }, [pocketMoneyRequestId, token]);

  if (isAccepted && requestData) {
    return (
      <MoneyDone
        title="용돈 보내기 완료"
        content={
          <>
            <div className="text-m">{requestData.child.name}</div>
            <div className="text-m text-main">{requestData.amount}원</div>
          </>
        }
        ps="남은 잔액 102,000원" // 남은 잔액 계산
      />
    );
  }

  return (
    <div className="flex flex-col min-h-screen pb-60">
      <Header pageTitle="용돈 요청 관리" headerType="normal" headerLink="/" />

      <div className="m-10 text-center flex flex-col flex-grow">
        <SmallBtn text="대기중" classes="mb-10 block mx-auto" />
        {requestData && <KidDemandedDetail data={requestData} />}

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
