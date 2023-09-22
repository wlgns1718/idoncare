import Header from "../components/common/Header";
import FullBtn from "../components/common/FullBtn";

function ARSPage() {
  return (
    <div>
      <Header pageTitle="출금 이체 동의" />
      <div className="my-20 py-10 bg-gray text-center">
        <div className="w-[20vw] h-[20vw] mx-auto">아이콘</div>
        <div className="text-m">
          ARS 출금이체 동의가 완료되면 <br /> 계좌인증단계로 이동 버튼을
          눌러주세요.
        </div>
        <div className="text-darkgray">
          인증전화가 안오거나 인증에 실패하셨나요?
        </div>
        <div className="text-darkgray">
          아래 재인증하기를 눌러 다시 시도해주세요
        </div>
        <div className="underline">재인증하기</div>
      </div>
      <FullBtn buttonText="ARS 출금 동의 완료" buttonLink="/wallet/recharge"/>
    </div>
  );
}

export default ARSPage;
