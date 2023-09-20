import React from 'react'
import { Link } from "react-router-dom";

type BottomMenuBoxPropx = {
    link: string;
    text: string | JSX.Element;
    classes?: string;
}

const BottomMenuBox: React.FC<BottomMenuBoxPropx> = ({ link, text, classes }) => {
    return (
      <div className="bg-light h-32 rounded-2xl text-center text-m flex items-center justify-center mb-8">
          <Link to={link} className={`${classes}`}>
              {text}
          </Link>
      </div>
    );
  };
  

export default BottomMenuBox;