import React, { useState, useEffect } from "react";
import Kid from "../../components/common/Kid";
import Modal from "../../components/common/Modal";
import FullBtn from "../../components/common/FullBtn";
import KidAdd from "../../components/connect/KidAdd";

type KidData = {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: string;
};

type KidsProps = {
  isOpen: boolean;
  setIsOpen: (value: boolean) => void;
  handleCloseModal: () => void;
};

const Kids: React.FC<KidsProps> = ({ isOpen, setIsOpen, handleCloseModal }) => {
  const [kidsData, setKidsData] = useState<KidData[]>([]);

  useEffect(() => {
    fetch("http://j9d209.p.ssafy.io:8081/api/relationship", {
      headers: {
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU3MDIyNzUsImV4cCI6MTY5NTc0NTQ3NX0.K7BmrgBzUerJrm8kkFvRzHp0Wp0C5I_ervHCtLxQ2gw",
      },
    })
    .then((response) => response.json())
    .then((result) => {
      if (result.data && result.data.relationList) {
        setKidsData(result.data.relationList);
      } else {
        console.error('Unexpected response:', result);
      }
    })    
      
      .catch((error) => console.error('Error:', error));
  }, []);

  return (
    <div className="flex flex-wrap justify-between">
        {kidsData.map(kid =>
            <Kid
                key={kid.relationshipId}
                className="w-1/4"
                is_connect={true}
                kname={kid.userName}
                onClick={() => setIsOpen(true)}
            />
        )}

      {isOpen && (
        <Modal>
          <div className="text-m m-16 text-center">
            자녀를 연결을
            <br />
            끊으시겠습니까?
          </div>
          <FullBtn
            buttonText="확인"
            buttonLink="/"
            onClick={handleCloseModal}
          />
        </Modal>
      )}

      <KidAdd className="w-1/4" />
    </div>
  );
};

export default Kids;
