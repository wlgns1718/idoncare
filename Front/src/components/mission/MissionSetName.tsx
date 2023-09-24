import { useSetRecoilState } from "recoil";
import { BottomSheet } from "../common/BottomSheet";
import { BottomSheetOpen } from "../../store/common/atoms";
import MissionBox from "./MissionBox";

function MissionSetName() {
  const setBottomSheetOpen = useSetRecoilState(BottomSheetOpen);

  return (
    <div>
      <div>어떤미션인가요?</div>
      <div className="bg-gray rounded-3xl p-8 gap-4 flex-col flex">
        <input
          type="text"
          placeholder="미션이름"
          className="bg-white w-full rounded-xl"
        ></input>
        <div
          className="bg-white rounded-xl text-center"
          onClick={() => {
            setBottomSheetOpen(true);
          }}
        >
          미션 선택하기
        </div>
      </div>
      <button
        onClick={() => {
          setBottomSheetOpen(true);
        }}
      >
        미션완료일 설정
      </button>
      <BottomSheet>
        <MissionBox />
      </BottomSheet>
    </div>
  );
}

export default MissionSetName;
