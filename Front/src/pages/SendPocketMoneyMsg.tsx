import React from 'react'
import { Link } from "react-router-dom";
import Header from "../components/common/Header";
import FullBtn from '../components/common/FullBtn';

function SendPocketMoneyMsg() {
  return (
    <div className="flex flex-col h-screen pb-60">
        <Header pageTitle="용돈 보내기" headerType="normal" headerLink="/" />

        <div className="flex-grow">
            <div>
                <div className="text-m mt-20 mb-32 text-center">
                    <span className="text-main m-2">이우철</span>님에게<br/>
                    <span className="text-main">1,000원</span>을 보낼게요            
                </div>

                <div className="bg-gray p-14 rounded-xl mt-5 text-s text-center">
                    <div className="flex justify-center items-center">
                        <img src="/icons/icon-emoji-1.png" alt="Icon" className="w-32 h-32"/>
                    </div>
                    <div className="bg-white rounded-lg pt-4 pb-4 mt-10 w-full">
                        <div className="text-darkgray">메세지 작성</div>
                    </div>
                </div>
            </div>

            </div>
            <FullBtn buttonText="확인" buttonLink="/MoneySendDone" />
        </div>
  )
}

export default SendPocketMoneyMsg