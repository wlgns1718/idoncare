import React from 'react'
import Header from "../components/common/Header";
import { Link } from "react-router-dom";
import YesNoBtn from '../components/common/YesNoBtn';

const ChildReguestMoney: React.FC = () => {
    return (
      <div className="flex flex-col min-h-screen pb-60">
          <Header pageTitle="용돈 요청 관리" headerType="normal" headerLink="/" />
  
          <div className="m-10 text-center flex flex-col flex-grow">

          <div className="bg-light p-2 w-32 rounded-3xl block mx-auto mt-10 mb-10 text-s text-main">
            대기중
        </div>

            
            <div className="text-m mb-10">
                <span className="text-main font-strong">이우철</span>님이<br/>
                용돈을 요청했어요
            </div>

            <div className="bg-gray p-14 rounded-xl mt-5 text-s text-center">
                <div className="flex justify-center items-center">
                    <img src="/icons/icon-emoji-1.png" alt="Icon" className="w-32 h-32"/>
                </div>
                <div className="text-darkgray">요청 메세지</div>
                <div className="mt-7 font-strong">
                    <span className="text-main font-strong">3,500원</span>이 필요해요
                </div>
            </div>

            <div className="bg-gray p-10 pr-6 rounded-xl mt-14 text-s text-center">
                <div className="flex justify-between mb-2">
                    <div>요청일</div>
                    <div>2023.09.10</div>
                </div>
                <div className="flex justify-between">
                    <div>취소예정일</div>
                    <div>2023.09.12</div>
                </div>
            </div>
            
            <YesNoBtn yesLink="/moneySendDone" />

        </div>
    </div>
  )
}

export default ChildReguestMoney
