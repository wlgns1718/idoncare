import React from 'react';
import Header from "../components/common/Header";
import TopChildList from "../components/common/TopChildList";
import MissonList from "../components/mission/MissonList";

const MissonPage: React.FC = () => {
  return (
    <div>
      <Header pageTitle="미션관리" headerLink="/" />
      <div className="my-6">
        <TopChildList />
      </div>
      <div className="mx-6">
        <MissonList />
      </div>
    </div>
  );
}

export default MissonPage;
