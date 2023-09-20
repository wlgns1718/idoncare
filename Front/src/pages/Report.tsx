import Header from "../components/common/Header";

function Report() {
  // const [dateScale, setDateScale] = useState(new Date("weekly"));

  return (
    <div>
      <Header pageTitle="활동보고서" />
      <div>
        <div className="flex p-2">
          <div className="flex flex-col">
            <div>7일간의 보고서 아이콘</div>
            <div>기간시작 ~ 기간끝</div>
          </div>
          <div className="flex px-2 py-1 bg-main rounded-3xl">
            <div className="px-4 py-1 bg-white rounded-3xl text-s">주</div>
            <div className="px-4 py-1 bg-main rounded-3xl text-s">월</div>
          </div>
        </div>
        <div className="w-full h-30">그래프 공간</div>
        <div>
          <div className="flex p-6 rounded-lg shadow-lg bg-gray">
            <div>아이콘</div>
            <div>지난주보다 1000만원 더 썼어요.</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Report;
