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

const Kid: React.FC<KidProps> = ({ imgSrc = "/icons/circle-pink.png", is_connect = true, kname, className, onClick, isSelected = true }) => {
  
  const handleClick = () => {
    if (is_connect && onClick) {
      onClick();
    }
  }

   const opacityClass = isSelected ? '' : 'opacity-50';

   return (
     <div className={`m-3 ${className}`} onClick={handleClick}>
       <img className={`${is_connect ? '' : 'opacity-50'} ${opacityClass}`} src={imgSrc} alt="icon" />
       <div className={`text-center text-l mt-6 ${opacityClass}`}>{kname}</div> {/* 수정된 부분 */}
     </div>
   )
}

export default Kid
