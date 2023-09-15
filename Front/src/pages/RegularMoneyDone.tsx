import React from 'react'
import FullBtn from '../components/common/FullBtn';

function RegularMoneyDone() {
    return (
        <div className="pb-60 flex flex-col h-screen justify-between">
            <div className="flex flex-col items-center justify-center flex-grow">
                <img src="/icons/icon-check.png" alt="Icon" 
                className="w-32 h-32 mx-auto block"/>
                <div className="text-l mt-10">정기용돈 등록 완료</div>
    
                <div className="bg-gray p-10 pr-6 rounded-xl mt-16 mb-5 w-5/6 text-center">
                    <div className="text-m">이우철</div>
                    <div className="text-m">매월 1일
                    <span className="text-main"> 1,000원</span>
                    </div>
                </div>
    
                <div className="text-darkgray text-s">
                    충전 잔액이 부족하면 이체되지 않습니다.
                </div>
    
            </div>
            <FullBtn className="mb-6"/>
        </div>
      )
    }

export default RegularMoneyDone