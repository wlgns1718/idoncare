import { ChangeEventHandler, MouseEventHandler, SyntheticEvent } from "react";
import { useNavigate } from "react-router-dom";

interface SearchFormProps {
  searchKeyword: string;
  onChange: ChangeEventHandler<HTMLInputElement>;
  resetKeyword: () => void;
  className?: string;
}

function SearchForm({
  searchKeyword,
  onChange,
  resetKeyword,
  className,
}: SearchFormProps) {
  const navigate = useNavigate();

  const handleSubmit = (event: SyntheticEvent<HTMLInputElement>) => {
    event.preventDefault();
    console.log("search keyword: ", searchKeyword);
  }

  const handleClick = () => {  
    console.log("search keyword: ", searchKeyword);
  }
  
  return (
    <div className={`${className}`}>
      <div className="flex items-center">
        <form
          className="bg-gray h-[35px] grow flex items-center px-4 rounded-lg"
          onSubmit={handleSubmit}
        >
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
            value={searchKeyword}
            onChange={onChange}
          />
          {searchKeyword && (
            <div
              className="bg-darkgray w-6 h-6 rounded-[50%] text-white text-center"
              onClick={() => {
                resetKeyword();
              }}
            >
              X
            </div>
          )}
        </form>
        <div className="p-4 h-[35px]" onClick={handleClick}>
          검색
        </div>
      </div>
      <div className="my-4">
        <span className="text-main">최근 180일 동안</span> 거래내역만 검색돼요
      </div>
    </div>
  );
}

export default SearchForm;
