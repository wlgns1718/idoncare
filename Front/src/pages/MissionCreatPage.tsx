import Header from '../components/common/Header'
import FullBtn from './../components/common/FullBtn';

import MissionSetName from './../components/mission/MissionSetName';

function MissionCreatPage() {
  return (
    <div>
      <Header pageTitle="미션 생성" />
      <div>
        <MissionSetName />
        <FullBtn />
      </div>
    </div>
  );
}

export default MissionCreatPage