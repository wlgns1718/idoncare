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

const SignupTypeSelect = ({ onNextStep, onSetInfo, userType, step }: SignupTypeSelectProps) => {
  const handleCheckValid = () => {
    if (userType === undefined) {
      return;
    }
    onNextStep();
  };

  return (
    <>
      <div
        onClick={() => {
          onSetInfo(step, "CHILD");
        }}
        className={`${
          userType === "CHILD" ? "scale-105" : "scale-95 grayscale"
        } transition duration-300 border-[5px]}`}
      >
        <Profile
          profileName="자식"
          profileImage="https://media.istockphoto.com/id/1338134336/photo/headshot-portrait-african-30s-man-smile-look-at-camera.webp?b=1&s=170667a&w=0&k=20&c=j-oMdWCMLx5rIx-_W33o3q3aW9CiAWEvv9XrJQ3fTMU="
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
          profileName="부모"
          profileImage="https://shotkit.com/wp-content/uploads/bb-plugin/cache/cool-profile-pic-matheus-ferrero-landscape-6cbeea07ce870fc53bedd94909941a4b-zybravgx2q47.jpeg"
        />
        <p>용돈을 줄래요!</p>
      </div>
      <div className="absolute bottom-0 w-[calc(100%-40px)] left-[20px]" onClick={handleCheckValid}>
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
