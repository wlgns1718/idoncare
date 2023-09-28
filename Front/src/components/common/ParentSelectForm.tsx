import React, { useState, useEffect } from "react";
import Header from "../common/Header";
import FullBtn from "../common/FullBtn";
import Parent from "../common/Parent";

interface Props {
  onNext: (parentUserId: number, parentUserName: string) => void;
}

type ParentData = {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: string;
};

const ParentSelectForm: React.FC<Props> = ({ onNext }) => {
  const [selectedParentId, setSelectedParentId] = useState<number | null>(null);
  const [selectedParentName, setSelectedParentName] = useState<string | null>(null);

  const [parentsData, setParentsData] = useState<ParentData[]>([]);

  useEffect(() => {
    fetch("http://j9d209.p.ssafy.io:8081/api/relationship", {
      headers: {
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjkxODMxMzczMDc2Njg4NTAwMDAsInJvbGUiOiJDSElMRCIsImlhdCI6MTY5NTg4NDg2NCwiZXhwIjoxNjk1OTI4MDY0fQ.0BpIXU9V-Zon3FclpY4TB_3MDGu8SPpiRrz9pNYbc7g",
      },
    })
      .then((response) => response.json())
      .then((result) => {
        if (result.data && result.data.relationList) {
          setParentsData(result.data.relationList);
        } else {
          console.error("Unexpected response:", result);
        }
      })
      .catch((error) => console.error("Error:", error));
    }, []); 

    const handleParentClick = (id:number, name:string) =>{
      if(selectedParentId === id){
          setSelectedParentId(null);
          setSelectedParentName(null);
      }else{
          setSelectedParentId(id);
          setSelectedParentName(name);
      }
    }

  return (
    <div className="flex flex-col h-screen pb-60">
      <Header pageTitle="용돈 받기" headerType="normal" headerLink="/" />

      <div className="m-10 text-center flex-grow">
       <div className="text-l mt-24 mb-28">부모님을 선택해주세요.</div>

       <div className="m-5 flex flex-wrap justify-center">
              {parentsData.map((parent) => (
                <Parent
                  key={parent.relationshipId}
                  is_connect={true}
                  isSelected={selectedParentId === parent.userId}
                  pname={parent.userName}
                  className="w-36 mr-6 mb-6"
                  onClick={() => handleParentClick(parent.userId,parent.userName)}
                />
              ))}
          </div>

      </div>
<FullBtn buttonText="다음" onClick={() => onNext(selectedParentId!, selectedParentName!)} isDone={selectedParentId !== null}/> 


    </div>
  );
};

export default ParentSelectForm;
