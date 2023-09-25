import React, { useState ,useEffect } from "react";
import Parent from "../../components/common/Parent";

interface Relation {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: Date;
}

interface Props {
  onParentSelected?: (selected: boolean) => void; // 추가된 prop
}

const Parents: React.FC<Props> = ({ onParentSelected }) => {
  const [selectedParent, setSelectedParent] = useState<string | null>(null);
  const [relationList, setRelationList] = useState<Relation[] | null>(null);

  const handleParentClick = (parentName: string) => {
    setSelectedParent(parentName);
   }

   useEffect(() => {
    fetch("http://j9d209.p.ssafy.io:8081/api/relationship", {
      headers: {
        'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjc3NDg5OTIwNTM0NjI3MTAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU2MTc4NTMsImV4cCI6MTY5NTY2MTA1M30.ehm3HVBigEsnET5z8qd7OCmqEDJjew9JQts8Fl8-WWg'
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
     })
      .catch(error => console.error('Error:', error));
    
    if (onParentSelected) onParentSelected(selectedParent !== null);
  }, [selectedParent]);
  


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
              <div className="text-m text-darkgray mt-72 ml-20">연결된 부모님이 없습니다.</div>
         )}
      </div>
    </div>
  );
  
};

export default Parents;
