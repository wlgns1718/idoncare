import React from 'react';
import Header from "../components/common/Header";
import TopChildList from "../components/common/TopChildList";
import MissonList from "../components/mission/MissonList";
import { useRecoilValue } from "recoil";
import { myRole } from "../store/common/selectors";

const MissonPage: React.FC = () => {
  const role = useRecoilValue(myRole);

  return (
    <div>
      <Header pageTitle="미션관리" headerLink="/" />
      {role === 'PARENT' && (
        <div className="my-6">
          <TopChildList />
        </div>
      )}
      <div className="mx-6">
        <MissonList />
      </div>
    </div>
  );
}

export default MissonPage;
