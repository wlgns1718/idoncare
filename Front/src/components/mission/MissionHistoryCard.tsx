import { MissionDataType } from "../../types/MissionTypes";
import MissionStateChip from "./MissionStateChip";



function MissionHistoryCard({ mission }: { mission: MissionDataType }) {
  return (
    <div className="my-4  mx-auto rounded-3xl w-[35vw] h-[40vw] flex-col shadow-[0_8px_15px_-2px_rgba(0,0,0,0.3)] shadow-blue-300 items-center">
      <MissionStateChip state={mission.type}/>
      <div className="mx-auto">{mission.title}</div>
      <div className="mx-auto">{mission.amount}</div>
    </div>
  );
}

export default MissionHistoryCard;