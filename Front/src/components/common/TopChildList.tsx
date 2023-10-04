import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Profile from "./Profile";
import defaultImg from "/icons/circle-pink.png";

interface Child {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: string;
}

const TopChildList: React.FC = () => {
  const [children, setChildren] = useState<Child[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.get('http://j9d209.p.ssafy.io:8081/api/relationship', {
        headers: {
          Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjI3NTA4OTk0NDY4NDM5ODAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYzMjAzNzQsImV4cCI6MTY5NjM2MzU3NH0.6byHM3nkXrY3rU4VaEFxxVc-3eXPVYF_0ClmslKOdqA'
        }
      });
      
      setChildren(result.data.data.relationList);
    };

    fetchData();
  }, []);

  return (
    <div className="m-8 mb-12">
      <div className="flex mx-auto overflow-x-auto no-scrollbar">
        {children.length > 0 ? (
          children.map((child) => (
            <div className="flex-none mx-6" key={child.relationshipId}>
              <Profile
                profileName={child.userName}
                profileImage={defaultImg}
                size="xsmall"
              />
            </div>
          ))
        ) : (
          <div className="ml-16 mt-4 mb-8 text-s text-darkgray">등록된 자녀가 없습니다.</div>
        )}
      </div>
    </div>
  );
  
}

export default TopChildList;
