// import React, { useState } from "react";
import Parent from "../../components/common/Parent";

const Parents: React.FC = () => {
//   const [selectedParent, setSelectedParent] = useState<string | null>(null); // 현재 선택된 Parent

//   const handleParentClick = (parentName: string) => {
    // setSelectedParent(parentName);
//   }

  return (
    <div className="flex flex-wrap justify-between">
      <div className="flex">
        <Parent 
          is_connect={true} 
          pname="엄마" 
        //   onClick={() => handleParentClick('엄마')} 
        //   isSelected={selectedParent === '엄마'}
        />
        <Parent 
          is_connect={true} 
          pname="아빠" 
        //   onClick={() => handleParentClick('아빠')} 
        //   isSelected={selectedParent === '아빠'}
        />
      </div>
    </div>
  );
};

export default Parents;
