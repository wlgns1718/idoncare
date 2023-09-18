import FullBtn from "../common/FullBtn";
import NewAccountHeader from "./NewAccountHeader";
import NewAccountInput from "./NewAccountInput";
import { NewAccountCreate } from "../../types/NewAccountCreateProps";

const NewAccountCreateStep3 = ({ onChangeStep, step }: NewAccountCreate) => {
  return (
    <div className="flex flex-col text-m">
      <NewAccountHeader step={step} />
      <div className="w-full p-[10px] bg-gray mb-[10px] text-l">
        <p className="text-darkgray">0123456789 (국민)</p>
      </div>
      <div className="w-full p-[10px] bg-light mb-[10px]">
        <p className="text-black">
          <span className="font-bold text-red-400 text-l">1원</span>을 보냈습니다.
        </p>
        <p className="text-darkgray text-s">
          본인의 계좌인지 확인하기 위해, 입력하신 계좌로 1원을 입금하였습니다.
        </p>
        <hr className="my-[5px] opacity-30" />
        <p className="text-black text-s">
          1. 계좌의 거래내역에서 입금된 1원의 입금자명을 확인해주세요.
        </p>
        <div className="border-darkgray border-[1px] border-solid bg-gray">
          <p className="text-center text-darkgray text-s">계좌 거래내역</p>
        </div>
        <div className="bg-white border-[1px] border-darkgray border-solid p-[10px] mb-[10px]">
          <div className="flex justify-between">
            <p className="text-darkgray text-s">입금자명</p>
            <p className="text-darkgray text-s">입금</p>
          </div>
          <div className="flex justify-between font-bold">
            <p className="text-black text-s">오픈****</p>
            <p className="text-black text-s">1원</p>
          </div>
        </div>
        <p className="text-black text-s">2. 확인한 입금자명의 숫자 4자리를 입력해 주세요.</p>
        <p className="text-darkgray">4글자 입력</p>
      </div>
      <NewAccountInput placeholder="입금자명(숫자4자리)" />
      <div onClick={() => onChangeStep(4)}>
        <FullBtn buttonText="다음" buttonLink="/newAccount" />
      </div>
    </div>
  );
};

export default NewAccountCreateStep3;
