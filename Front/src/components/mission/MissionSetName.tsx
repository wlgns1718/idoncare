import { useRecoilState, useSetRecoilState } from "recoil";
import { BottomSheet } from "../common/BottomSheet";
import { BottomSheetOpen } from "../../store/common/atoms";
import MissionBox from "./MissionBox";
import { createMissionData } from "../../store/mission/atoms";

function MissionSetName() {
  const setBottomSheetOpen = useSetRecoilState(BottomSheetOpen);

  const [createMissionDataState,setCreateMissionData] = useRecoilState(createMissionData);

  return (
    <div>
      <div className="text-l text-center my-6">어떤미션인가요?</div>
      <div className="bg-gray rounded-3xl p-8 gap-4 flex-col flex text-s">
        <input
          type="text"
          placeholder="미션이름"
          value={createMissionDataState.title}
          className="bg-white w-full rounded-xl p-3 text-center"
          onChange={(event) => {
            setCreateMissionData({
              ...createMissionDataState,
              title: event.target.value,
            });
          }}
        ></input>
        <div
          className="bg-soft rounded-xl text-center p-3"
          onClick={() => {
            setBottomSheetOpen(true);
          }}
        >
          미션 선택하기
        </div>
      </div>
      <div className="mx-auto text-center my-10">
        <button
          onClick={() => {
            setBottomSheetOpen(true);
          }}
        >
          미션완료일 설정
        </button>
      </div>
      <div className="mx-auto text-center my-10">
        <button
          onClick={() => {
            setBottomSheetOpen(true);
          }}
        >
          미션마감시간 설정
        </button>
      </div>
      <BottomSheet size={80}>
        <MissionBox />
      </BottomSheet>
    </div>
  );
}

export default MissionSetName;
