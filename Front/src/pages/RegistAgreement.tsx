import Header from '../components/common/Header';
import FullBtn from '../components/common/FullBtn';

function RegistAgreement() {
  return (
    <div>
      <Header pageTitle="출금 이체 동의" />
      <div>
        <div className="py-8">
          연결중인 계좌의 결제/송금 이행에 동의해주세요.
        </div>
        <div className="p-6 bg-gray border-zinc-700/50 border rounded-xl">
          <div className="px-3 py-3 flex">
            <div className="w-[30%] text-center">이름</div>
            <div>김부모</div>
          </div>
          <hr />
          <div className="px-3 py-3 flex">
            <div className="w-[30%] text-center">은행</div>
            <div>은행 이름</div>
          </div>
          <hr />
          <div className="px-3 py-3 flex">
            <div className="w-[30%] text-center">계좌번호</div>
            <div>계좌번호</div>
          </div>
        </div>
        <div className="py-6">
          {
            "아이돈케어의 결제 및 송금 서비스를 이용하기 위해 출금 이체 동의를 진행하여 ARS 인증으로 이어나갑니다."
          }
        </div>
      </div>
      <FullBtn buttonText="동의하기" buttonLink="ARS" />
    </div>
  );
}

export default RegistAgreement