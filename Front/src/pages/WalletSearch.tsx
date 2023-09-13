import { React, useState } from "react";
import { useNavigate } from "react-router-dom";

const WalletSearch = () => {
  const navigate = useNavigate("")

  const [searchKeyword, setSearchKeyword] = useState();

  const handdleSearchKeyword = (e) => {
    setSearchKeyword(e.target.value)
    console.log(searchKeyword)
  }

  return (
    <div className="m-10">
      <div className="flex items-center">
        <form className="bg-gray h-[35px] grow flex items-center px-4 rounded-lg">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={1.5}
            stroke="currentColor"
            className={`w-6 h-6`}
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
            />
          </svg>
          <input
            type="text"
            placeholder="찾을 내용을 입력해주세요"
            className="bg-gray ml-2 grow focus:outline-none"
            onChange={handdleSearchKeyword}
          />
        </form>
        <button
          className="p-4 h-[35px]"
          onClick={() => {
            navigate("/wallet");
          }}
        >
          취소
        </button>
      </div>
      <div className="my-4">
        <span className="text-main">최근 180일 동안</span> 거래내역만 검색돼요
      </div>
    </div>
  );
};

export default WalletSearch;
