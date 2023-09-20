import React, { useEffect, useState } from "react";
import Header from "../components/common/Header";
import QRCode from "react-qr-code";

function QRcodePurchase() {
  const [expiryTime, setExpiryTime] = useState(Date.now() + 3 * 60 * 1000); // 현재 시간 + 3분
  const [timeLeft, setTimeLeft] = useState(expiryTime - Date.now());

  useEffect(() => {
    const timer = setInterval(() => {
      const now = Date.now();
      if (now >= expiryTime) {
        clearInterval(timer);
        setTimeLeft(0);
      } else {
        setTimeLeft(expiryTime - now);
      }
    }, 1000);

    return () => clearInterval(timer);
  }, [expiryTime]);

  const increaseTime = () => { 
    setExpiryTime(Date.now() + 3 * 60 * 1000);
  }
  return (
    <div>
      <Header pageTitle="QR 코드 결제" headerLink="/purchase" headerType="normal" />
      <div className="flex justify-center bg-darkgray rounded-2xl py-20">
        <QRCode value="as;dfj;adsf" size={200} />
      </div>
      <div>
        <div className="bg-gray flex px-10 py-4 my-6 justify-between rounded-3xl">
          <div className="text-black">지갑잔액</div>
          <div className=" text-main ">99,000 원</div>
        </div>
        <div className="w-full h-[10vh] bg-gray  rounded-3xl flex items-center justify-center">
          <div className="py-3 px-6 text-white bg-darkgray rounded-3xl w-auto ">
            남은시간 {Math.floor(timeLeft / 60000)}:
            {((timeLeft % 60000) / 1000).toFixed(0).padEnd(2, "0")}
          </div>
          <div onClick={()=>increaseTime()}>연장</div>
        </div>
      </div>
    </div>
  );
}

export default QRcodePurchase;
