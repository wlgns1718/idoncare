import React from "react";
import { Link } from "react-router-dom";

type SendMoneyBoxProps = {
  link: string;
  bgColor?: string;
  textColor?: string;
  text: string | JSX.Element;
  classes?: string;
};

const SendMoneyBox: React.FC<SendMoneyBoxProps> = ({ link, bgColor, textColor, text, classes }) => {
  return (
    <Link to={link} className={`bg-${bgColor} text-${textColor} w-full text-center text-m bg-opacity-90 box-content rounded-xl p-12 mt-4 ${classes}`}>
      {text}
    </Link>
  );
};

export default SendMoneyBox;
