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

type ParentCheckData = {
  relationshipRequestId: number;
  parentName: string;
  parentPhoneNumber: string;
};

const ParentSetting: React.FC = () => {
  const [parentsData, setParentsData] = useState<ParentData[]>([]);
  const [parentCheckRequests, setParentCheckRequests] = useState<
    ParentCheckData[]
  >([]);

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

    fetch("http://j9d209.p.ssafy.io:8081/api/relationship/child/request", {
      headers: {
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjkxODMxMzczMDc2Njg4NTAwMDAsInJvbGUiOiJDSElMRCIsImlhdCI6MTY5NTg4NDg2NCwiZXhwIjoxNjk1OTI4MDY0fQ.0BpIXU9V-Zon3FclpY4TB_3MDGu8SPpiRrz9pNYbc7g",
      },
    })
      .then((response) => response.json())
      .then((result) => setParentCheckRequests(result.data.requests))
      .catch((error) => console.error("Error:", error));
  }, []);

  return (
    <div className="flex flex-col justify-between h-screen">
      <div>
        <Header pageTitle="부모님" headerType="normal" headerLink="/" />

        <div className="mt-56">
          <div className="text-l text-center">내 보호자</div>
          <div className="m-5 flex flex-wrap">
            {parentsData.map((parent) => (
              <Parent
                key={parent.relationshipId}
                is_connect={true}
                isSelected={true}
                pname={parent.userName}
                className="w-36"
              />
            ))}
          </div>
        </div>
      </div>

      <div className="mb-20">
        {parentCheckRequests.map((request, index) => (
          <div
            key={request.relationshipRequestId}
            className={index !== 0 ? "mt-5" : ""}
          >
            <ParentCheck
              name={request.parentName}
              phoneNumber={request.parentPhoneNumber}
              relationshipRequestId={request.relationshipRequestId}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default ParentSetting;
