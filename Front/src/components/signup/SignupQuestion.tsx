import pencil from "../../assets/imgs/signup/pencil.png";
import smile from "../../assets/imgs/signup/smile.png";
import { useState } from "react";
import FullBtn from "../common/FullBtn";

type Question = string;
type Answer = string;

interface SignupQuestionProps {
  question: Question;
  answer?: Answer;
  onNextStep: () => void;
}

const SignupQuestion = ({ onNextStep, question, answer = "" }: SignupQuestionProps) => {
  console.log(onNextStep);
  const [answerText, setAnswerText] = useState<string>(answer);
  return (
    <div className="flex flex-col w-full">
      <div className="relative rounded-[10px] bg-red-50 w-[220px] h-[80px] p-[20px] mb-[40px] flex items-center">
        <img className="absolute w-[100px] h-[100px] top-[-80px]" src={smile} alt="smile img" />
        <p className="text-m">{question}</p>
      </div>
      <div className="flex justify-end">
        <div className="flex items-center justify-end bg-main rounded-[10px] w-[160px] h-[40px] px-[10px]">
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
      <FullBtn />
    </div>
  );
};

export default SignupQuestion;
