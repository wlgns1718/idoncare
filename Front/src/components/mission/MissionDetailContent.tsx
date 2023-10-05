// import useComma from "../../hooks/useComma";
import MissionStateChip from './MissionStateChip';
import { MissionDetailType } from "../../types/MissionTypes";

function MissionDetailContent({ mission }: { mission: MissionDetailType | null }) {
  if (!mission) return <div>No mission data</div>;
  
  return (
    <div className="mx-6 my-10">
      <div className="flex-col flex justify-center items-center gap-5">
      <MissionStateChip state={mission.type} />
        <div className="text-m">{mission.title}</div>
        {/* <div className="text-s text-thick">{useComma(mission.mount)} 원</div> */}
        <div className="text-s text-thick">{mission.amount} 원</div>
      </div>
      <div className="my-10 bg-gray rounded-3xl p-12 text-center text-m">
        {mission.message}
      </div>
      <div className="overflow-hidden rounded-3xl my-10">
        <div className="bg-mediumgray p-4 text-s text-center">{mission.childName}</div>
        <div className="py-4 bg-gray ">
  <table className="flex-col w-full text-center">
    <tbody>
      <tr>
        <td>요청일 </td>
        <td>{mission.createdAt}</td>
      </tr>
    </tbody>
  </table>


        </div>
      </div>
    </div>
  );
}

export default MissionDetailContent;
