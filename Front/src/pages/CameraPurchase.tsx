import { useEffect, useRef } from "react";
import Header from "../components/common/Header";
import QrScanner from "qr-scanner";
import useComma from "../hooks/useComma";
import { useRecoilValue } from "recoil";
import { userBalanace } from "../store/wallet/atoms";
import axios from "axios";
import { baseUrl } from "../apis/url/baseUrl";
import AxiosHeader from "../apis/axios/AxiosHeader";
import { userToken } from "../store/common/selectors";

function CameraPurchase() {
  const balance = useRecoilValue(userBalanace);
  const token = useRecoilValue(userToken);

  const handleScan = (result: QrScanner.ScanResult) => {
    console.log(typeof result.data);
    const parseData = JSON.parse(result.data);

    axios
      .post(baseUrl + "api/virtual", parseData, AxiosHeader({ token }))
      .then((res) => {
        console.log(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const videoRef = useRef(null);

  const QrOptions = {
    // 핸드폰의 경우, 외부 카메라인지 셀프카메라인지
    preferredCamera: "environment",
    // 1초당 몇번의 스캔을 할 것인지? ex) 1초에 5번 QR 코드 감지한다.
    maxScansPerSecond: 2,
    // QR 스캔이 일어나는 부분을 표시해줄 지 (노란색 네모 테두리가 생긴다.)
    highlightScanRegion: true,
  };

  useEffect(() => {
    navigator.mediaDevices.getUserMedia({
      video: true,
    });
    const videoElem = videoRef.current;
    if (videoElem) {
      const qrScanner = new QrScanner(
        videoElem,
        (result) => handleScan(result),
        QrOptions
      );
      qrScanner.start();

      return () => qrScanner.destroy();
    }
  }, []);
  return (
    <div>
      <Header
        pageTitle="QR 코드 스캔"
        headerLink="/purchase"
        headerType="normal"
      />
      <div className="flex justify-center bg-darkgray rounded-2xl py-20">
        <video ref={videoRef} className="w-full h-full object-cover" />
      </div>
      <div>
        <div className="bg-gray flex px-10 py-4 my-6 justify-between rounded-3xl">
          <div className="text-black">지갑잔액</div>
          <div className=" text-main ">{useComma(balance)} 원</div>
        </div>
        <p>{}</p>
        <div className="w-full h-[10vh] bg-gray  rounded-3xl flex items-center justify-center">
          <div className="py-3 px-6 text-white bg-darkgray rounded-3xl w-auto ">
            결제코드 입력
          </div>
        </div>
      </div>
    </div>
  );
}

export default CameraPurchase;
