import React, { ReactNode } from 'react';
// import YesNoBtn from './YesNoBtn';

interface BottomModalProps {
    // onClose: () => void;
    // onSave: () => void;
    children: ReactNode;
}

const BottomModal: React.FC<BottomModalProps> = ({ children }) => {
  // const handleYesClick = () => {
  //   onSave();
  //   onClose();
  // };
  
    return (
      <div className="fixed inset-0 flex items-center justify-center z-50">
        <div className="bg-black opacity-50 absolute inset-0"></div>
        <div className="relative w-full max-w-md mx-auto py-4 px-6 bg-white rounded shadow-lg z-10">
          {children}
            {/* <YesNoBtn
                noText="아니오"
                yesText="예"
                noLink=""
                yesLink="" 
                className="max-w-md mx-auto"
                onYesClick={handleYesClick}
                onNoClick={onClose}
            /> */}
        </div>
      </div>
    );
  };
  
  export default BottomModal;