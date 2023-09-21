import React from "react";

type ParentProps = {
  imgSrc?: string;
  is_connect?: boolean;
  pname?: React.ReactNode;
};

const Parent: React.FC<ParentProps> = ({
  imgSrc = "/bg/circle-blue.png",
  is_connect = false,
  pname,
}) => {
  return (
    <div className="m-3">
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
