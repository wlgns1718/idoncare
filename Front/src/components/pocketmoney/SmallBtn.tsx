import React from "react";
import { Link } from "react-router-dom";

type SmallBtnProps = {
  link?: string;
  text: string;
  bgColor?: string;
  textColor?: string;
  classes?: string;
};

const SmallBtn: React.FC<SmallBtnProps> = ({ 
    link = "",
    text, 
    bgColor = "bg-light",
    textColor = "text-main",
    classes = ""}) => {
  
  return (
    <div>
      <Link to={link} 
            className={`p-3 pl-7 pr-7 rounded-3xl inline-block text-s ${bgColor} ${textColor} ${classes}`}>
        {text}
      </Link>
    </div>
  );
};

export default SmallBtn;
