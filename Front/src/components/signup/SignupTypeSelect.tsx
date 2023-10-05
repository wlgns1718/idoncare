import FullBtn from "../common/FullBtn";
import Profile from "../common/Profile";
import Img1 from "../../assets/imgs/profile/부모1.png";
import Img2 from "../../assets/imgs/profile/부모2.png";
import Img3 from "../../assets/imgs/profile/부모3.png";
import Img4 from "../../assets/imgs/profile/부모4.png";
import Img5 from "../../assets/imgs/profile/부모5.png";
import Img6 from "../../assets/imgs/profile/아이1.png";
import Img7 from "../../assets/imgs/profile/아이2.png";
import Img8 from "../../assets/imgs/profile/아이3.png";
import Img9 from "../../assets/imgs/profile/아이4.png";
import Img10 from "../../assets/imgs/profile/아이5.png";
import { useEffect, useState } from "react";

type Type = string;
type Step = number;
interface SignupTypeSelectProps {
  onNextStep: () => void;
  onSetInfo: (setType: number, value: string) => void;
  userType?: Type | undefined;
  step: Step;
}

const profileImages = [
  Img1,
  Img2,
  Img3,
  Img4,
  Img5,
  Img6,
  Img7,
  Img8,
  Img9,
  Img10,
];

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
  const [randomParent, setRandomParent] = useState(0);
  const [randomChild, setRandomChild] = useState(0);

  useEffect(()=>{
    setRandomParent(Math.floor(Math.random() * 5))
    setRandomChild(Math.floor(Math.random() * 5) + 5);
  }, [])

  return (
    <>
      <div className="flex-col mx-[14vw] mt-[-100px]">
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
            profileImage={profileImages[randomChild]}
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
            profileImage={profileImages[randomParent]}
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
