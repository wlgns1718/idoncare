import React from "react";
import Header from "../components/common/Header";
// import KidPhoneForm from "../components/connect/KidPhoneForm";
import FullBtn from "../components/common/FullBtn";

const KidRegister: React.FC = () => {
  return (
    <div className="flex flex-col h-screen">
      <Header pageTitle="자녀 등록" headerType="normal" headerLink="/" />

      <div className="flex-grow">
        <div className="text-m text-center mt-48 mb-16">
          자녀의 전화번호를 입력해주세요.
        </div>
        {/* <KidPhoneForm /> */}
      </div>

      <FullBtn buttonText="등록" />
    </div>
  );
};

export default KidRegister;
