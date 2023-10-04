import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header from "../components/common/Header";
import KidPhoneForm from "../components/connect/KidPhoneForm";
import FullBtn from "../components/common/FullBtn";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/selectors";
import AxiosHeader from "../apis/axios/AxiosHeader";
import { baseUrl } from "../apis/url/baseUrl";

const KidRegist: React.FC = () => {
  const token = useRecoilValue(userToken);
  const [childPhoneNumber, setChildPhoneNumber] = useState("");
  const navigate = useNavigate();

  const handleChildPhoneNumberSubmit = async () => {
    try {
      const response = await axios.post(
        baseUrl + `api/relationship/request`,
        { childPhoneNumber: String(childPhoneNumber) },
        AxiosHeader({ token })
      );

      console.log(response.data);
      console.log(childPhoneNumber);

      alert("등록되었습니다.");
      navigate("/");
    } catch (error) {
      console.error(error);
      console.log(childPhoneNumber);

      alert("오류가 발생했습니다.");
    }
  };

  return (
    <div className="flex flex-col h-screen">
      <Header pageTitle="자녀 등록" headerType="normal" headerLink="/" />

      <div className="flex-grow">
        <div className="text-m text-center mt-48 mb-16">
          자녀의 전화번호를 입력해주세요.
        </div>
        <KidPhoneForm onPhoneNumberChange={setChildPhoneNumber} />
      </div>

      <FullBtn buttonText="등록" onClick={handleChildPhoneNumberSubmit} />
    </div>
  );
};

export default KidRegist;
