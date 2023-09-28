import React, { useState, useEffect } from "react";
import Header from "../common/Header";
import FullBtn from "../common/FullBtn";
// import MyKidList from "../pocketmoney/MyKidList";
import Kid from "../common/Kid";

interface Props {
  onNext: () => void;
}

type KidData = {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: string;
};

const KidSelectForm: React.FC<Props> = ({ onNext }) => {
  const [selectedKidIds, setSelectedKidIds] = useState<number[]>([]);

  const [kidsData, setKidsData] = useState<KidData[]>([]);

  useEffect(() => {
    fetch("http://j9d209.p.ssafy.io:8081/api/relationship", {
      headers: {
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjIzOTA4ODU1ODUwMjgwNzAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTU5MDEwMDEsImV4cCI6MTY5NTk0NDIwMX0.7BL9OQesx4R058h4YnQSL9m74BV-J6EJod9_WsSXV7I",
      },
    })
      .then((response) => response.json())
      .then((result) => {
        if (result.data && result.data.relationList) {
          setKidsData(result.data.relationList);
        } else {
          console.error("Unexpected response:", result);
        }
      })
      .catch((error) => console.error("Error:", error));
    }, []); 

    const handleKidClick = (id:number) =>{
        if(selectedKidIds.includes(id)){
            setSelectedKidIds(selectedKidIds.filter(kidId=>kidId !== id))
        }else{
            setSelectedKidIds([...selectedKidIds,id])
        }
    }

  return (
    <div className="flex flex-col h-screen pb-60">
      <Header pageTitle="용돈 보내기" headerType="normal" headerLink="/" />

      <div className="m-10 text-center flex-grow">
       <div className="text-l mt-24 mb-28">자녀를 선택해주세요.</div>

        <div className="m-5 flex flex-wrap justify-center">
            {kidsData.map((kid) => (
              <Kid
                key={kid.relationshipId}
                is_connect={true}
                isSelected={selectedKidIds.includes(kid.relationshipId)}
                kname={kid.userName}
                className="w-36 mr-10 mb-6"
                onClick={() => handleKidClick(kid.relationshipId)}
              />
            ))}
          </div>

      </div>
      <FullBtn buttonText="다음" onClick={onNext} isDone={selectedKidIds.length > 0}/> 

    </div>
  );
};

export default KidSelectForm;
