import { Link, useNavigate } from "react-router-dom";

const Icon = ({ h = 6, w = 6 }) => {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth={1.5}
      stroke="currentColor"
      className={`w-${w} h-${h}`}
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
      />
    </svg>
  );
};

const TradeList = () => {
  return (
    <div className="">
      <div className="px-4 my-4">
        <div>9월 7일</div>
        <hr />
      </div>
      {/* 항목 */}
      <div className="">
        <TradeListItem />
        <TradeListItem />
        <TradeListItem />
      </div>
    </div>
  );
};

const TradeListItem = () => {
  return (
    <div className="flex m-5">
      <div className="bg-gray w-[40px] h-[40px] rounded-[50%] flex items-center justify-center">
        <Icon />
      </div>
      <div className="flex justify-between w-full">
        <div className="ml-10">
          <div>이우철 용돈</div>
          <div>11:20</div>
        </div>
        <div>
          <div className="text-left text-main">-2,500 원</div>
          <div className="text-left text-darkgray">남은 돈 15,000 원</div>
        </div>
      </div>
    </div>
  );
};

const Wallet = () => {
  const navigate = useNavigate();
  return (
    <div>
      <p>Wallet</p>
      <div className="bg-yellow-100">
        <Link to="/">Login</Link>
      </div>
      <div className="m-8">
        <div>
          <div className="p-10 text-white h-[140px] rounded-2xl [background:linear-gradient(270deg,_#1c51ad_20%,_rgba(28,_81,_173,_0.3))]">
            <div className="ml-3">
              <div>잔액</div>
              <div className="text-m">99,000 원</div>
            </div>
            <div className="flex justify-between px-6 py-5 text-center">
              <div>
                <div className="h-10 bg-neutral-800 w-14">아이콘</div>
                <div>용돈 관리</div>
              </div>
              <div>
                <div className="h-10 bg-neutral-800 w-14">아이콘</div>
                <div>충전</div>
              </div>
              <div>
                <div className="h-10 bg-neutral-800 w-14">아이콘</div>
                <div>송금</div>
              </div>
            </div>
          </div>
          <div>
            <div className="flex justify-between p-6">
              <div className="flex gap-5">
                <div className="rounded-[15px] w-[60px] h-[30px] text-center text-main text-m bg-light">
                  전체
                </div>
                <div>입금</div>
                <div>출금</div>
              </div>
              <button onClick={() => navigate("search")}>
                <Icon />
              </button>
            </div>
            <div className="flex items-center justify-between h-16 p-4 bg-gray rounded-xl">
              <Icon />
              <div>{"2023년 09월"}</div>
              <Icon />
            </div>
            <div>
              {/* 날짜별 */}
              <TradeList />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Wallet;
