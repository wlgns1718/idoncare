import Header from "../components/common/Header";
import MissionDetailContent from "../components/mission/MissionDetailContent";
import FullBtn from "../components/common/FullBtn";
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function MissionDetailPage () {
  
const [missionData, setMissionData] = useState(null);
const { missionId } = useParams();

useEffect(() => {
    axios.get(`https://j9d209.p.ssafy.io:9081/api/mission/${missionId}`, {
      headers:{
        Authorization:
          "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjI3NTA4OTk0NDY4NDM5ODAwMDAsInJvbGUiOiJQQVJFTlQiLCJpYXQiOjE2OTYzMjAzNzQsImV4cCI6MTY5NjM2MzU3NH0.6byHM3nkXrY3rU4VaEFxxVc-3eXPVYF_0ClmslKOdqA",
      },
    })
    .then((response) => {
      setMissionData(response.data.data);
      console.log(response.data.data);
    });
}, [missionId]);

return (
    <div>
      <Header pageTitle="미션" />
      <div className="mx-8">
        {missionData && <MissionDetailContent mission={missionData} />}
        <FullBtn buttonText="확인" />
      </div>
    </div>
);

}

export default MissionDetailPage;

