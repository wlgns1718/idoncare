import Header from "../components/common/Header";
import TopChildList from "../components/common/TopChildList";
import MissonList from "../components/mission/MissonList";

function MissonPage() {
  return (
    <div>
      <Header pageTitle="미션관리" headerLink="/" />
      <div className="my-6">
        <TopChildList></TopChildList>
      </div>
      <div className="mx-6">
        <MissonList />
      </div>
    </div>
  );
}

export default MissonPage;
