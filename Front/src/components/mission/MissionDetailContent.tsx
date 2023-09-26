import useComma from "../../hooks/useComma";
import MissionStateChip from './MissionStateChip';

function MissionDetailContent() {
  return (
    <div className="mx-6 my-10">
      <div className="flex-col flex justify-center items-center gap-5">
        <MissionStateChip state={"COMPLETE"} />
        <div className="text-m">내 방 청소하기</div>
        <div className="text-s text-thick">{useComma(2500)} 원</div>
      </div>
      <div className="my-10 bg-gray rounded-3xl p-12 text-center text-m">
        엄마 청소 열심히 할게요 ㅎㅎ
      </div>
      <div className="overflow-hidden rounded-3xl my-10">
        <div className="bg-mediumgray p-4 text-s text-center">XXX님에게 요청</div>
        <div className="py-4 bg-gray ">
          <table className="flex-col w-full text-center">
            <tr>
              <td>요청일 </td>
              <td> 2023.09.09</td>
            </tr>
            <tr>
              <td>취소 예정일 </td>
              <td> 2023.09.09</td>
            </tr>
            <tr>
              <td>완료 예정일 </td>
              <td> 2023.09.09</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  );
}

export default MissionDetailContent;
