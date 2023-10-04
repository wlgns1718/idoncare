import { useState, useEffect } from "react";
import axios from "axios";
import useAccessTokenState from "../../hooks/useAccessTokenState";
import { baseUrl } from "../../apis/url/baseUrl";

interface APIResult<T> {
  code: number;
  error: string;
  data: T;
}

interface RelationType {
  relationshipId: number;
  userId: number;
  userName: string;
  createdAt: Date;
}

interface RelationResult {
  relationList: Array<RelationType>;
}

const Kids = () => {
  const accessToken = useAccessTokenState();
  const [childs, setChilds] = useState<Array<RelationType>>([]);

  useEffect(() => {
    //아이 목록 불러오기
    (async () => {
      const result = await axios.get<APIResult<RelationResult>>(
        baseUrl + "api/relationship",
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      const { code, data } = result.data;

      if (code === 200) {
        //제대로 불러왔으면
        setChilds(data.relationList);
      }
    })();
  }, []);

  return (
    <div>
      {childs.map((child) => {
        return <div>{child.userName}</div>;
      })}
    </div>
  );
};

export default Kids;
