import React from "react";

type KidProps = {
  imgSrc?: string;
  is_connect?: boolean;
  kname?: React.ReactNode;
  className?: string;
  onClick?: () => void;
};

const Kid: React.FC<KidProps> = ({
  imgSrc = "/bg/circle-pink.png",
  is_connect = true,
  kname,
  className,
  onClick,
}) => {
  return (
    <div
      className={`m-3 ${className}`}
      onClick={is_connect ? onClick : undefined}
    >
      <img
        className={`${is_connect ? "" : "opacity-50"}`}
        src={imgSrc}
        alt="icon"
      />
      <div className="text-center text-l mt-6">{kname}</div>
    </div>
  );
};

export default Kid;
