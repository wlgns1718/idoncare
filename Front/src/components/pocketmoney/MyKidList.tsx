import React, { useState } from 'react';
import Kid from "../common/Kid"

const MyKidList: React.FC = () => {
  const [selectedKids, setSelectedKids] = useState<string[]>([]); // 현재 선택된 Kids

  const handleKidClick = (kidName: string) => {
    if (selectedKids.includes(kidName)) {
      setSelectedKids(selectedKids.filter(kid => kid !== kidName)); // 이미 선택되어 있다면 제거
    } else {
      setSelectedKids([...selectedKids, kidName]); // 아니라면 추가
    }
  }

  return(
    <div className="flex">
      <Kid onClick={() => handleKidClick('우철')} isSelected={selectedKids.includes('우철')} kname="우철"/>
      <Kid onClick={() => handleKidClick('제성')} isSelected={selectedKids.includes('제성')} kname="제성"/>
    </div>
  )
}

export default MyKidList;
