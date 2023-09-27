import { useRecoilState } from 'recoil';
import Header from '../components/common/Header'
import { createMissionData } from '../store/mission/atoms';
import FullBtn from './../components/common/FullBtn';

import MissionSetName from './../components/mission/MissionSetName';

function MissionCreatPage() {
  const [missionData, setMissionData] =
    useRecoilState(createMissionData);

  return (
    <div>
      <Header pageTitle="미션 생성" />
      <div className="mx-10">
        <MissionSetName missionData={missionData} setMissionData={setMissionData}/>
        <FullBtn buttonLink="money" isDone={missionData.title?true:false} />
      </div>
    </div>
  );
}

export default MissionCreatPage