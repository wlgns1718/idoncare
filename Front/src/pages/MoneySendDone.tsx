import React from 'react'
import { Link } from "react-router-dom";
import FullBtn from '../components/common/FullBtn';

const MoneySendDone: React.FC = () => {
  return (
    <div className="pb-60 flex flex-col h-screen justify-between">
        <div className="flex flex-col items-center justify-center flex-grow">
            <img src="/icons/icon-check.png" alt="Icon" 
            className="w-32 h-32 mx-auto block"/>
            <div className="text-l mt-10">용돈 보내기 완료</div>

            <div className="bg-gray p-10 pr-6 rounded-xl mt-16 mb-5 w-5/6 text-center">
                <div className="text-m">이우철</div>
                <div className="text-m text-main">1,000원</div>
            </div>

            <div className="text-darkgray text-sm">
                남은 잔액 102,000원
            </div>

        </div>
        <FullBtn className="mb-6"/>
    </div>
  )
}

export default MoneySendDone
