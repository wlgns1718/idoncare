import { ChangeEvent, useEffect, useRef, useState } from "react";
import Header from "../components/common/Header";
import QrScanner from "qr-scanner";
import useComma from "../hooks/useComma";
import { useRecoilValue } from "recoil";
import { userBalanace } from "../store/wallet/atoms";
import axios from "axios";
import { baseUrl } from "../apis/url/baseUrl";
import AxiosHeader from "../apis/axios/AxiosHeader";
import { userToken } from "../store/common/selectors";
import { useNavigate } from "react-router";
import { QRcodeDataPayload } from "./QRcodePurchase";

function CameraPurchase() {
  const balance = useRecoilValue(userBalanace);
  const token = useRecoilValue(userToken);
  const navigate = useNavigate();

  const [isScanned, setIsScanned] = useState(true);
  const [amount, setAmount] = useState(0);
  const [scanData, setScanData] = useState<QRcodeDataPayload | null>(null);

  const handleScan = (result: QrScanner.ScanResult) => {
    console.log(typeof result.data);
    const parseData = JSON.parse(result.data);
    if (!parseData) {
      return;
    }
    setIsScanned(true);
    setScanData(parseData);
    setAmount(parseData.money);
  };

  const handleAmount = (event: ChangeEvent<HTMLInputElement>) => {
    setAmount(Number(event.target.value));
  };

  const payRequest = () => {
    if (amount == 0) {
      return;
    }
    axios
      .post(baseUrl + "api/virtual", scanData, AxiosHeader({ token }))
      .then((res) => {
        console.log(res.data);
        navigate(
          {
            pathname: "/done",
          },
          {
            state: {
              // eslint-disable-next-line react-hooks/rules-of-hooks
              title: `${useComma(amount)} 원`,
              content: "결제 완료",
              ps: "성공적으로 결제되었습니다.",
            },
          }
        );
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
    maxScansPerSecond: 1,
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
      <div className="mx-8">
        <div className="flex justify-center bg-darkgray rounded-2xl w-full overflow-hidden">
          <video ref={videoRef} className="w-full h-full" />
        </div>
        <div>
          <div className="bg-gray flex px-10 py-4 my-6 justify-between rounded-3xl">
            <div className="text-black">지갑잔액</div>
            <div className=" text-main ">{useComma(balance)} 원</div>
          </div>
          <p>{}</p>
          <div className="w-full p-10 bg-gray  rounded-3xl flex items-center justify-center">
            {isScanned && scanData?.payType == "slow" && (
              <div className="py-3 px-6 text-white bg-darkgray rounded-3xl w-auto">
                <input
                  type="number"
                  className=" bg-white m-2 rounded-lg text-end outline-0 mx-3 p-2"
                  value={amount ? amount : undefined}
                  onChange={handleAmount}
                />{" "}
                원
              </div>
            )}
            {isScanned && scanData?.payType == "fast" && (
              <div className="py-3 px-6 text-white bg-darkgray rounded-3xl w-auto mb-4 text-center">
                {amount} 원
              </div>
            )}
            <div>
              <div
                className="py-3 px-6 text-white bg-darkgray rounded-3xl w-auto"
                onClick={() => payRequest()}
              >
                {isScanned ? "결제 진행" : "스캔 중"}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CameraPurchase;
