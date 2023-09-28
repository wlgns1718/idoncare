import React from 'react'

type KidProps = {
  imgSrc?: string;
  is_connect?: boolean;
  kname?: React.ReactNode;
  className?: string; 
  onClick?: () => void;
  childUserId?: string;
  isSelected?: boolean;
}

const Kid: React.FC<KidProps> = ({ imgSrc = "/icons/circle-pink.png", is_connect = true, kname, className, onClick, isSelected }) => {
  
    const handleClick = () => {
        if (is_connect && onClick) {
            onClick();
        }
    }

    const selectedClassName = isSelected ? "scale-105" : "scale-95 grayscale";

    return (
        <div className={`m-3 ${selectedClassName} ${className}`} onClick={handleClick}>
            <img className={`${is_connect ? '' : 'opacity-50'}`} src={imgSrc} alt="icon" />
            <div className="text-center text-l mt-6">{kname}</div>
        </div>
    )
}

export default Kid
