import React, { useState } from "react";
import Modal from "../common/Modal";
import FullBtn from "../common/FullBtn";

type PYesNoBtnProps = {
  relationshipRequestId: number;
};


const PYesNoBtn: React.FC<PYesNoBtnProps> = ({relationshipRequestId}) => {
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [modalContent, setModalContent] = useState("");

  const sendRequest = (processType: string) => {
    const body = JSON.stringify({
      relationshipRequestId,
      process: processType,
    })

    console.log(body);

    fetch("http://j9d209.p.ssafy.io:8081/api/relationship/child/request", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjY1Mzc2Njc2NTAwODUyMTAwMDAsInJvbGUiOiJDSElMRCIsImlhdCI6MTY5NTc5MzY4MCwiZXhwIjoxNjk1ODM2ODgwfQ.ms_k330ZMaMjcqT4LAt__R3g3Rd3T8LBDwRrwExmt9E"
      },
      body,
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
  };

  const handleYesClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    sendRequest("ACCEPT");
    setModalContent("수락");
    setModalIsOpen(true);
  };

  const handleNoClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    sendRequest("REJECT");
    setModalContent("거절");
    setModalIsOpen(true);
  };

  return (
    <div className="flex mt-auto">
      <div className="flex justify-between w-full">
        <button
          onClick={handleYesClick}
          className="inline-block p-2 pl-4 pr-4 text-white bg-main rounded-lg text-s mr-3"
        >
          수락
        </button>
        <button
          onClick={handleNoClick}
          className="inline-block p-2 pl-4 pr-4 text-darkgray bg-white rounded-lg text-s"
        >
          거절
        </button>
      </div>

      {modalIsOpen && (
        <Modal>
          <div className="text-m m-16">
            김슬기님의 요청을 <br />
            {modalContent}했습니다.
          </div>
          <FullBtn
            buttonText="확인"
            buttonLink="/"
            onClick={() => setModalIsOpen(false)}
          />
        </Modal>
      )}
    </div>
  );
};

export default PYesNoBtn;
