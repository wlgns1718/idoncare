import React from "react";
import Kid from "../../components/common/Kid";
import Modal from "../../components/common/Modal";
import FullBtn from "../../components/common/FullBtn";
import KidAdd from "./KidAdd";

type KidsProps = {
  isOpen: boolean;
  setIsOpen: (value: boolean) => void;
  handleCloseModal: () => void;
};

const Kids: React.FC<KidsProps> = ({ isOpen, setIsOpen, handleCloseModal }) => {
  return (
    <div className="flex flex-wrap justify-between">
      <Kid
        className="w-1/4"
        is_connect={true}
        onClick={() => setIsOpen(true)}
      />

      <Kid
        className="w-1/4"
        is_connect={true}
        onClick={() => setIsOpen(true)}
      />

      <Kid
        className="w-1/4"
        is_connect={false}
        onClick={() => setIsOpen(true)}
      />

      <KidAdd className="w-1/4" />

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
    </div>
  );
};

export default Kids;
