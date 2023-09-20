import React from 'react';

type ParentProps = {
    imgSrc?: string;
    is_connect?: boolean;
    children?: React.ReactNode;
}

const Parent: React.FC<ParentProps> = ({ imgSrc = "/icons/icon-emoji-1.png", is_connect = false, children }) => {
  return (
    <div className={`bg-darkgray rounded-full w-48 h-48 flex items-center justify-center ml-5 mr-5 ${is_connect ? '' : 'bg-opacity-50'}`}>
        <img className={`${is_connect ? '' : 'opacity-50'}`} src={imgSrc} alt="icon" />
        {children}
    </div>
  );
};

export default Parent;
