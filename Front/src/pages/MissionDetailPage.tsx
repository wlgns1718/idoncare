import Header from "../components/common/Header";
import MissionDetailContent from "../components/mission/MissionDetailContent";
import FullBtn from "../components/common/FullBtn";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/selectors";
import AxiosHeader from "../apis/axios/AxiosHeader";
import { baseUrl } from "../apis/url/baseUrl";

function MissionDetailPage() {
  const token = useRecoilValue(userToken);
  const [missionData, setMissionData] = useState(null);
  const { missionId } = useParams();

  useEffect(() => {
    axios
      .get(baseUrl + `api/mission/${missionId}`, AxiosHeader({ token }))
      .then((response) => {
        setMissionData(response.data.data);
        console.log(response.data.data);
      });
  }, [missionId, token]);

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
