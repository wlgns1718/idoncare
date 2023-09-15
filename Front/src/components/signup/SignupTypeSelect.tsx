import FullBtn from "../common/FullBtn";
import Profile from "../common/Profile";

type Type = string;
interface SignupTypeSelectProps {
  onNextStep: () => void;
  onSetType: (type: Type) => void;
  userType?: Type | undefined;
}

const SignupTypeSelect = ({ onNextStep, onSetType, userType }: SignupTypeSelectProps) => {
  return (
    <>
      <div
        onClick={() => {
          onSetType("CHILDREN");
        }}
        className={`${
          userType === "CHILDREN" ? "scale-105" : "scale-95 grayscale"
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
          onSetType("PARENT");
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
      <div className="absolute bottom-0 w-[calc(100%-40px)] left-[20px]" onClick={onNextStep}>
        {/* <div
          className={`absolute w-full bg-red-200 h-[40px] ${userType !== undefined && "hidden"}`}
        ></div> */}
        <FullBtn buttonText="다음" buttonLink="/signup" className="w-full" />
      </div>
    </>
  );
};

export default SignupTypeSelect;
