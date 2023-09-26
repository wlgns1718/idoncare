import React, { useState, useEffect } from "react";
import Header from "../components/common/Header";
import Parent from "../components/common/Parent";
import ParentCheck from "../components/connect/ParentCheck";

type ParentData = {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: string;
};

const ParentSetting: React.FC = () => {
  const [parentsData, setParentsData] = useState<ParentData[]>([]);

  useEffect(() => {
    fetch("http://j9d209.p.ssafy.io:8081/api/relationship", {
      headers: {
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjU2OTQwOTMxMTM5NTM3MDAwMDAsInJvbGUiOiJDSElMRCIsImlhdCI6MTY5NTcwNTI0NiwiZXhwIjoxNjk1NzQ4NDQ2fQ.SYeeIXTusf65dwt5DEaniRuFTawUPNoYG46jnEbi1ng",
      },
    })
      .then((response) => response.json())
      .then((result) => {
        if (result.data && result.data.relationList) {
          setParentsData(result.data.relationList);
        } else {
          console.error('Unexpected response:', result);
        }
      })
      .catch((error) => console.error('Error:', error));
  }, []);

  return (
    <div className="flex flex-col justify-between h-screen">
      <div>
        <Header pageTitle="부모님" headerType="normal" headerLink="/" />

        <div className="mt-56">
          <div className="text-l text-center">내 부모님</div>
          <div className="m-5 flex">
            
{parentsData.map(parent =>
    <Parent 
        key={parent.relationshipId}
        is_connect={true} 
        isSelected={true} 
        pname={parent.userName} 
    />
)}

          </div>
        </div>
      </div>

      <div className="mb-20">
        <ParentCheck name="김슬기" phoneNumber="010-1234-1234" />
      </div>
    </div>
  );
};

export default ParentSetting;
