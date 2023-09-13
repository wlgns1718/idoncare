import React from "react";
import kakaoLogo from "../../assets/imgs/login/kakaoLogo.png";
const LoginButton = () => {
  return (
    <div className="w-full bg-[#fae100] rounded-[10px] h-[60px] flex justify-center items-center mt-[100px]">
      <img className="w-[20px] mx-[2px] mt-[4px]" src={kakaoLogo} />
      <p className="text-m mx-[2px]">카카오톡으로 로그인</p>
    </div>
  );
};

export default LoginButton;
