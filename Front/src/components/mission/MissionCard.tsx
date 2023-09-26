export type MissionCardType = {
  title: string;
  img: string;
};

interface MissionCardProps {
  mission: MissionCardType;
}

function MissionCard({ mission }: MissionCardProps) {
  return (
    <div
      className="my-4 mx-auto p-4 rounded-3xl w-[35vw] h-[40vw] flex-col shadow-[0_8px_15px_-2px_rgba(0,0,0,0.3)] shadow-blue-300 items-center"
      onClick={() => {}}
    >
      <div className="mx-auto text-center text-s my-6">{mission.title}</div>
      <img src="../../assets/imgs/singup.smile.png" alt="" />
    </div>
  );
}

export default MissionCard;
