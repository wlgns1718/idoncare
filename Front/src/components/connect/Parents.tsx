import React, { useState ,useEffect } from "react";
import Parent from "../../components/common/Parent";

interface Relation {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: Date;
}

interface Props {
  onParentSelected?: (selected: boolean) => void;
  onSelectedParentChange?: (parentName: string | null) => void;
}

const Parents: React.FC<Props> = ({ onParentSelected, onSelectedParentChange }) => {
  const [selectedParent, setSelectedParent] = useState<string | null>(null);
  const [relationList, setRelationList] = useState<Relation[] | null>(null);

  const handleParentClick = (parentName: string) => {
    setSelectedParent(parentName);
    if (onSelectedParentChange) { // 수정된 부분
      onSelectedParentChange(parentName); // 수정된 부분
    }
    if (onParentSelected) { // 추가된 부분
      onParentSelected(true); // 추가된 부분: 선택된 부모가 없음을 알림 
    }
   };

   useEffect(() => {
    fetch("http://j9d209.p.ssafy.io:8081/api/relationship", {
      headers: {
        'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjM0MDIxNDgyODk3NjQ3NDAwMDAsInJvbGUiOiJDSElMRCIsImlhdCI6MTY5NTg1NzQ3OSwiZXhwIjoxNjk1OTAwNjc5fQ.Av0SJNIRSVQUbkrqpjLPd-aI134C9UPE-GKP5Wpnm7g'
      }
    })
      .then(response => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then(data => { 
        console.log(data);
        setRelationList(data.relationList);
      
        if (onParentSelected) {
          onParentSelected(data.relationList && data.relationList.length > 0); // 수정된 부분: 연결된 부모 여부 알림
        }
      })
      .catch(error => console.error('Error:', error));
    
      if (onSelectedParentChange && selectedParent === null) { // 수정된 부분 
        onSelectedParentChange(null); //수정됨 
        if (onParentSelected) { // 추가된 부분
          onParentSelected(true); // 추가된 부분: 선택된 부모가 없음을 알림 
        }
      }
    }, [selectedParent, onSelectedParentChange, onParentSelected]);


  return (
    <div className="flex flex-wrap justify-between">
      <div className="flex">
        {relationList ? (
            relationList.length > 0 ? 
              relationList.map((relation) =>
                <Parent 
                  key={relation.relationshipId}
                  is_connect={true} 
                  pname={relation.userName} 
                  onClick={() => handleParentClick(relation.userName)} 
                  isSelected={selectedParent === relation.userName}
                />
              )
            : (<div>관계 없음</div>)
         ) : (
              <div className="text-m text-darkgray mt-72 ml-20">연결된 보호자가 없습니다.</div>
         )}
      </div>
    </div>
  );
  
};

export default Parents;
