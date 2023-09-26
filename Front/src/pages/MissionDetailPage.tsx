import Header from "../components/common/Header";
import MissionDetailContent from "../components/mission/MissionDetailContent";
import FullBtn from "../components/common/FullBtn";

function MissionDetailPage () {

  return (
    <div>
      <Header pageTitle="미션" />
      <div className="mx-8">
        <MissionDetailContent />
        <FullBtn buttonText="확인" />
      </div>
    </div>
  );
}

export default MissionDetailPage;
