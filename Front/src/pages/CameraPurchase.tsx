import React, { useState } from 'react'
import Header from '../components/common/Header';
import QrReader from "react-web-qr-reader";

function CameraPurchase() {
  const delay = 500;

  const previewStyle = {
    height: 240,
    width: 240,
  };

  const [result, setResult] = useState("No result");

  const handleScan = (result : string) => {
    if (result) {
      setResult(result);
    }
  };

  const handleError = (error: string) => {
    console.log(error);
  };

  return (
    <div>
      <Header
        pageTitle="QR 코드 스캔"
        headerLink="/purchase"
        headerType="normal"
      />
      <div className="flex justify-center bg-darkgray rounded-2xl py-20">
        <QrReader
          delay={delay}
          style={previewStyle}
          onError={handleError}
          onScan={()=>handleScan}
          onLoad={() => {}}
        />
      </div>
      <div>
        <div className="bg-gray flex px-10 py-4 my-6 justify-between rounded-3xl">
          <div className="text-black">지갑잔액</div>
          <div className=" text-main ">99,000 원</div>
        </div>
        <p>{result}</p>
        <div className="w-full h-[10vh] bg-gray  rounded-3xl flex items-center justify-center">
          <div className="py-3 px-6 text-white bg-darkgray rounded-3xl w-auto ">
            결제코드 입력
          </div>
        </div>
      </div>
    </div>
  );
}

export default CameraPurchase