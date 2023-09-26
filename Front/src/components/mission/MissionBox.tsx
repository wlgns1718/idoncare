import Chip from "../common/Chip";
import { useState } from "react";
import MissionCard, { MissionCardType } from "./MissionCard";

export type MissionCategory = {
  type: string;
  text: string;
};

const categorys: MissionCategory[] = [
  {
    type: "Recent",
    text: "최근",
  },
  {
    type: "excercise",
    text: "운동하기",
  },
  {
    type: "study",
    text: "공부하기",
  },
  {
    type: "housework",
    text: "집안일하기",
  },
  {
    type: "family",
    text: "효도",
  },
  {
    type: "dog",
    text: "반려견",
  },
];

const missionCards: MissionCardType[] = [
  {
    title: "설거지하기",
    img: "../../assets/images/mission/1.png",
  },
  {
    title: "설거지하기",
    img: "../../assets/images/mission/1.png",
  },
  {
    title: "설거지하기",
    img: "../../assets/images/mission/1.png",
  },
  {
    title: "설거지하기",
    img: "../../assets/images/mission/1.png",
  },
  {
    title: "설거지하기",
    img: "../../assets/images/mission/1.png",
  },
  {
    title: "설거지하기",
    img: "../../assets/images/mission/1.png",
  },
];

function MissionBox() {
  const [currentCategory, setCurrentCategory] = useState(categorys[0].type);

  const handleCategory = (category: string) => {
    setCurrentCategory(category);
  };
  return (
    <div>
      <div className="text-m text-center my-6">미션함</div>
      <div className="flex gap-5 overflow-x-auto no-scrollbar my-6">
        {categorys.map((category) => (
          <div className="flex-none">
            <Chip
              key={category.type}
              isSelected={currentCategory === category.type}
              category={category}
              handler={handleCategory}
            />
          </div>
        ))}
      </div>
      <div className="grid grid-cols-2 items-center px-4">
        {missionCards.map((mission, index) => (
          <MissionCard key={index} mission={mission} />
        ))}
      </div>
    </div>
  );
}

export default MissionBox;
