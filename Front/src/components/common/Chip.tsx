import React from "react";
import { ChipProps } from "../../types/ChipProps";

const Chip = ({ isSelected, category, handler }: ChipProps) => {
  return (
    <div
      className={`rounded-[15px] w-[60px] h-[25px] text-center text-m ${
        isSelected ? "text-main bg-light" : "text-darkgray bg-gray"
      }`}
      onClick={() => handler(category.type)}
    >
      {category.text}
    </div>
  );
};

export default Chip;
