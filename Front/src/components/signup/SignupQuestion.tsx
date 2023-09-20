import pencil from "../../assets/imgs/signup/pencil.png";
import smile from "../../assets/imgs/signup/smile.png";
import { useState } from "react";
import FullBtn from "../common/FullBtn";

type Question = string;
type Answer = string;
type Caption = string;
type Step = number;

interface SignupQuestionProps {
  question: Question;
  answer?: Answer;
  caption?: Caption;
  onNextStep: () => void;
  onSetInfo: (setType: number, value: string) => void;
  step: Step;
}

const SignupQuestion = ({
  onNextStep,
  question,
  answer = "",
  caption = "",
  onSetInfo,
  step,
}: SignupQuestionProps) => {
  const [answerText, setAnswerText] = useState<string>(answer);
  const handleSetInfo = () => {
    onSetInfo(step, answerText);
    onNextStep();
  };
  return (
    <div className="flex flex-col w-full">
      <div className="relative rounded-[30px] bg-red-50 w-[220px] h-[80px] p-[20px] mb-[40px] flex items-center">
        <img className="absolute w-[100px] h-[100px] top-[-80px]" src={smile} alt="smile img" />
        <div className="flex-col">
          <p className="text-m">{question}</p>
          <p className={`${caption === undefined && "hidden"} text-s flex`}>{caption}</p>
        </div>
      </div>
      <div className="flex justify-end">
        <div className="flex items-center justify-end bg-main rounded-[20px] w-[160px] h-[40px] px-[10px]">
          <input
            className="w-full h-full text-white bg-transparent text-m"
            type="text"
            value={answerText}
            onChange={(e) => setAnswerText(e.target.value)}
            maxLength={8}
          />
          <img className="w-[20px] h-[20px]" src={pencil} alt="편집 이미지" />
        </div>
      </div>
      <div className="absolute bottom-0 w-[calc(100%-40px)] left-[20px]" onClick={handleSetInfo}>
        <FullBtn buttonText="다음" buttonLink="/signup" className="w-full" />
      </div>
    </div>
  );
};

export default SignupQuestion;
