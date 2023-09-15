import React from 'react'
import Header from "../components/common/Header";
import NumberPlate from '../components/common/NumberPlate';
import FullBtn from '../components/common/FullBtn';

function SendRegularMoney() {
    return (
        <div className="flex flex-col h-screen pb-60">
            <Header pageTitle="정기용돈 등록" headerType="normal" headerLink="/" />
    
            <div className="m-10 text-center flex-grow">
                <div className="text-l mt-16 mb-16">
                    이우철<br/>정기용돈 등록
                </div>

                <div className="bg-gray p-3 rounded-lg text-sm mb-10">
                    이체주기 (내일부터 시작됩니다)
                    </div>
                
    
                <div className="text-l text-main font-strong mb-5">
                    1,000원
                </div>

            </div>
    
            <div className='mt-auto text-center'>
              <p>잔액 버튼 | 잔액 버튼 | 잔액 버튼</p>
              <NumberPlate bottomLeftText="00"/>
              <FullBtn buttonText="다음" buttonLink="/RegularMoneyDone" />
            </div>
        </div>
      )
    }

export default SendRegularMoney