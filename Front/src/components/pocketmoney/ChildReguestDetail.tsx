import React from "react";

type ChildReguestMoneyDetailProps = {
  name: string;
  message: string;
  amount: string;
  requestDate: string;
  cancelDate: string;
};

const ChildReguestMoneyDetail: React.FC<ChildReguestMoneyDetailProps> = ({ 
    name, 
    message, 
    amount,
    requestDate,
    cancelDate }) => {
  
  return (
      <div>
        <div className="text-m mb-10">
            <span className="text-main font-strong">{name}</span>님이<br/>
            용돈을 요청했어요
        </div>

        <div className="bg-gray p-14 rounded-xl mt-5 text-s text-center">
            <div className="flex justify-center items-center">
                <img src="/icons/icon-emoji-1.png" alt="Icon" className="w-32 h-32"/>
            </div>
            <div className="text-darkgray">{message}</div>
            <div className="mt-7 font-strong">
                <span className="text-main font-strong">{amount}</span>이 필요해요
            </div>
        </div>

        <div className="bg-gray p-10 pr-6 rounded-xl mt-14 text-s text-center">
          < div className = "flex justify-between mb -2 ">
            <div>요청일</div>
              {requestDate}
          </ div >

          < div className = "flex justify-between" >
            <div>취소예정일</div>
              {cancelDate}
          </ div >
      </ div >
      </div>
   );
};

export default ChildReguestMoneyDetail;
