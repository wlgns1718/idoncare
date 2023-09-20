import React from 'react'

function TransferSelectForm() {
  
  return (
    <div>
      <div>
        <h1>누구에게 보낼래요?</h1>
        <div>
          <div>가족</div>
          <div>계좌</div>
        </div>
      </div>
      <div>
        <div>자녀 1</div>
        <div>자녀 2</div>
      </div>
      <div>
        <div>은행선택</div>
        <div>계좌번호 입력</div>
        <div>계좌 확인</div>

        <div>등록된 계좌</div>
        <div
          onClick={() => {
            navigate("confirm");
          }}
        >
          농협 2348234982343
        </div>
      </div>
    </div>
  );
}

export default TransferSelectForm