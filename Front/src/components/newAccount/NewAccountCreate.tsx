import FullBtn from "../common/FullBtn";
import NewAccountCheckBox from "./NewAccountCheckBox";
import NewAccountInput from "./NewAccountInput";
import NewAccountSelectBox from "./NewAccountSelectBox";
import NewAccountToggleButton from "./NewAccountToggleButton";

const NewAccountCreate = () => {
  return (
    <div className="flex flex-col text-m">
      <p className="bg-light text-s p-[10px] mb-[20px]">
        오픈뱅킹 서비스를 사용하기 위한 고객님의 이용동의 및 본인인증 절차를 진행합니다.
      </p>
      <p className="text-s mb-[10px]">제공되는 정보 : 성명, 연계정보(CI)값</p>
      <div className="flex items-center text-m mb-[10px]">
        <NewAccountInput placeholder="성명" />
        <NewAccountToggleButton first="내국인" second="외국인" />
      </div>
      <div className="flex items-center text-m mb-[10px]">
        <NewAccountInput placeholder="생년월일(8자리)" />
        <NewAccountToggleButton first="남" second="여" />
      </div>
      <div className="mb-[10px]">
        <NewAccountInput placeholder="휴대폰번호(숫자만)" />
      </div>
      <div className="border-solid border-[3px] border-darkgray mb-[10px]">
        <NewAccountSelectBox />
      </div>
      <div className="mb-[10px]">
        <NewAccountCheckBox text="서비스 이용 및 개인정보처리 동의" isAccept={true} />
      </div>
      <div className="mb-[10px]">
        <NewAccountCheckBox text="개인정보 제3자 제공 동의" isAccept={false} />
      </div>
      <div>
        <FullBtn buttonText="다음" />
      </div>
    </div>
  );
};

export default NewAccountCreate;
