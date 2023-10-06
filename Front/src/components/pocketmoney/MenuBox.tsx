import React from "react";
import { Link } from "react-router-dom";

type SendMoneyBoxProps = {
  link: string;
  bgColor: string;
  textColor: string;
  text: string | JSX.Element;
  classes?: string;
};

const SendMoneyBox: React.FC<SendMoneyBoxProps> = ({
  link,
  bgColor,
  textColor,
  text,
  classes,
}) => {
  return (
    <Link
      to={link}
      className={`${bgColor} ${textColor} 
      flex justify-center items-center
      box-content rounded-xl h-40 w-60
      p-4  border-4 text-3xl ${classes}`}
    >
      {text}
    </Link>
  );
};

export default SendMoneyBox;
