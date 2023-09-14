import pencil from "../../assets/imgs/signup/pencil.png";
import smile from "../../assets/imgs/signup/smile.png";

const SignupName = () => {
  return (
    <div className="flex flex-col w-full">
      <div className="relative rounded-[10px] bg-red-50 w-[220px] h-[80px] p-[20px] mb-[40px] flex items-center">
        <img className="absolute w-[100px] h-[100px] top-[-80px]" src={smile} alt="smile img" />
        <p className="text-m">이름을 알려주세요.</p>
      </div>
      <div className="flex justify-end">
        <div className="flex items-center justify-end bg-main rounded-[10px] w-[120px] h-[40px] px-[10px]">
          <p className="text-white text-m"></p>
          <img className="w-[20px] h-[20px]" src={pencil} alt="편집 이미지" />
        </div>
      </div>
    </div>
  );
};

export default SignupName;
