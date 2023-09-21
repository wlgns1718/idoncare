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
      className={`${bgColor} ${textColor} box-content rounded-xl h-40 w-60 pt-9 pl-10 border-4 ${classes}`}
    >
      {text}
    </Link>
  );
};

export default SendMoneyBox;
