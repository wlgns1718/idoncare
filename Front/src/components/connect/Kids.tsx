import React, { useState, useEffect } from "react";
import Kid from "../../components/common/Kid";
import Modal from "../../components/common/Modal";
import FullBtn from "../../components/common/FullBtn";
import KidAdd from "../../components/connect/KidAdd";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../../store/common/selectors";
import AxiosHeader from "../../apis/axios/AxiosHeader";
import { baseUrl } from "../../apis/url/baseUrl";

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
  const token = useRecoilValue(userToken);
  const [kidsData, setKidsData] = useState<KidData[]>([]);

  useEffect(() => {
    axios
      .get(baseUrl + `api/relationship`, AxiosHeader({ token }))
      .then((response) => {
        if (response.data.data && response.data.data.relationList) {
          setKidsData(response.data.data.relationList);
        } else {
          console.error("Unexpected response:", response);
        }
      })
      .catch((error) => console.error("Error:", error));
  }, []);

  return (
    <div className="flex flex-wrap justify-between">
      {kidsData.map((kid) => (
        <Kid
          key={kid.relationshipId}
          className="w-1/4"
          is_connect={true}
          kname={kid.userName}
          onClick={() => setIsOpen(true)}
        />
      ))}

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
