import React, { useState, useEffect } from 'react';
import Kid from "../common/Kid"

interface Relation {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: Date;
}

interface Props {
  onKidsSelected?: (count: number) => void;
}

const MyKidList: React.FC<Props> = ({ onKidsSelected }) => {
  const [selectedKids, setSelectedKids] = useState<string[]>([]);
  const [relationList, setRelationList] = useState<Relation[] | null>(null);

   // handleKidClick 함수 추가
   const handleKidClick = (kidName: string) => {
    if (selectedKids.includes(kidName)) {
      setSelectedKids(selectedKids.filter(kid => kid !== kidName));
    } else {
      setSelectedKids([...selectedKids, kidName]);
    }
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
      
    if (onKidsSelected) onKidsSelected(selectedKids.length);
  }, [selectedKids]);

  
   // API 요청 중일 때 또는 관계가 없는 경우
   if (!relationList || relationList.length === 0) return <div className="text-m text-darkgray mt-72">연결된 자녀가 없습니다.</div>;

   // 관계가 있는 경우
   return (
     <div className="flex">
       {relationList.map(relation =>
         <Kid 
           key={relation.relationshipId}
           onClick={() => handleKidClick(relation.userName)} 
           isSelected={selectedKids.includes(relation.userName)} 
           kname={relation.userName}
         />
       )}
     </div>
   );
}

export default MyKidList;
