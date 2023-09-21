import React from "react";
import PYesNoBtn from "../connect/PYesNoBtn";

type ParentCheckProps = {
  name: string;
  phoneNumber: string;
};

const ParentCheck: React.FC<ParentCheckProps> = ({ name, phoneNumber }) => {
  return (
    <div className="p-3 flex justify-between items-center bg-mediumgray rounded-lg">
      <div className="ml-4">
        <span className="text-s mr-5">{name}</span>
        <span className="text-s">{phoneNumber}</span>
      </div>

      <PYesNoBtn />
    </div>
  );
};

export default ParentCheck;
