import React from 'react'

function RechargeAccountList() {
  return (
    <div>
      <div>출금 계좌</div>
      <div>
        <div className="w-full h-[80px] bg-gray rounded-xl flex ">
          <div>아이콘</div>
          <div className='flex'>
            <div className='flex-col'>
              <div>NH 농협</div>
              <div>12983723498732</div>
            </div>
            <div>▼</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RechargeAccountList