import FullBtn from "../common/FullBtn";
import Profile from "../common/Profile";

type Type = string;
type Step = number;
interface SignupTypeSelectProps {
  onNextStep: () => void;
  onSetInfo: (setType: number, value: string) => void;
  userType?: Type | undefined;
  step: Step;
}

const SignupTypeSelect = ({
  onNextStep,
  onSetInfo,
  userType,
  step,
}: SignupTypeSelectProps) => {
  const handleCheckValid = () => {
    if (userType === undefined) {
      return;
    }
    onNextStep();
  };

  return (
    <>
      <div className="flex">
        <div
          onClick={() => {
            onSetInfo(step, "CHILD");
          }}
          className={`${
            userType === "CHILD" ? "scale-105" : "scale-95 grayscale"
          } transition duration-300 border-[5px]}`}
        >
          <Profile
            size="large"
            profileName="자식"
            profileImage="https://shotkit.com/wp-content/uploads/bb-plugin/cache/cool-profile-pic-matheus-ferrero-landscape-6cbeea07ce870fc53bedd94909941a4b-zybravgx2q47.jpeg"
          />
          <p>용돈을 받을래요!</p>
        </div>
        <div
          onClick={() => {
            onSetInfo(step, "PARENT");
          }}
          className={`${
            userType === "PARENT" ? "scale-105" : "scale-95 grayscale"
          } transition duration-300}`}
        >
          <Profile
            size="large"
            profileName="부모"
            profileImage="https://shotkit.com/wp-content/uploads/bb-plugin/cache/cool-profile-pic-matheus-ferrero-landscape-6cbeea07ce870fc53bedd94909941a4b-zybravgx2q47.jpeg"
          />
          <p>용돈을 줄래요!</p>
        </div>
      </div>
      <div
        className="absolute bottom-0 w-[calc(100%-40px)] left-[20px]"
        onClick={handleCheckValid}
      >
        <FullBtn
          buttonText="다음"
          buttonLink="/signup"
          className="w-full"
          isDone={userType !== undefined}
        />
      </div>
    </>
  );
};

export default SignupTypeSelect;
