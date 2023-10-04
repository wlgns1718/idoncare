import useComma from "../../hooks/useComma";
import MissionStateChip from './MissionStateChip';
import { MissionResistType } from "../../types/MissionTypes";

function MissionDetailContent({ mission }: { mission: MissionResistType }) {
  return (
    <div className="mx-6 my-10">
      <div className="flex-col flex justify-center items-center gap-5">
      <MissionStateChip state={mission.type} />
        <div className="text-m">{mission.title}</div>
        <div className="text-s text-thick">{useComma(mission.amount)} 원</div>
      </div>
      <div className="my-10 bg-gray rounded-3xl p-12 text-center text-m">
        {mission.beforeMessage}
      </div>
      <div className="overflow-hidden rounded-3xl my-10">
        <div className="bg-mediumgray p-4 text-s text-center">XXX님에게 요청</div>
        <div className="py-4 bg-gray ">
  <table className="flex-col w-full text-center">
    <tbody>
      <tr>
        <td>요청일 </td>
        {/* <td>{mission.create_at}</td> */}
      </tr>
      <tr>
        <td>취소 예정일 </td>
        <td> 2023.09.09</td>
      </tr>
      <tr>
        <td>완료 예정일 </td>
        <td> 2023.09.09</td>
      </tr>
    </tbody>
  </table>


        </div>
      </div>
    </div>
  );
}

export default MissionDetailContent;
