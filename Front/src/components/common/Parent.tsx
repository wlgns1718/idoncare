import React from "react";

type ParentProps = {
  imgSrc?: string;
  is_connect?: boolean;
  pname?: React.ReactNode;
  onClick?: () => void;
  isSelected?: boolean;
};

const Parent: React.FC<ParentProps> = ({
  imgSrc = "/icons/circle-blue.png",
  is_connect = false,
  pname,
  onClick,
  isSelected,
}) => {

const className = isSelected ? "scale-105" : "scale-95 grayscale";

return (
    <div 
      className={`m-3 ${className}`}
      onClick={onClick}
    >
      <img
        className={`${is_connect ? "" : "opacity-50"}`}
        src={imgSrc}
        alt="icon"
      />
      <div className="text-center text-l mt-6">{pname}</div>
    </div>
);
};

export default Parent;
