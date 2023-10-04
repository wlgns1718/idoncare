import React, { useEffect, useState } from "react";
import Profile from "./Profile";
import defaultImg from "/icons/circle-pink.png";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../../store/common/selectors";
import AxiosHeader from "../../apis/axios/AxiosHeader";
import { baseUrl } from "../../apis/url/baseUrl";

interface Child {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: string;
}

const TopChildList: React.FC = () => {
  const token = useRecoilValue(userToken);
  const [children, setChildren] = useState<Child[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.get(
        baseUrl + `api/relationship`,
        AxiosHeader({ token })
      );

      setChildren(result.data.data.relationList);
    };

    fetchData();
  }, [token]);

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
          <div className="ml-16 mt-4 mb-8 text-s text-darkgray">
            등록된 자녀가 없습니다.
          </div>
        )}
      </div>
    </div>
  );
};

export default TopChildList;
