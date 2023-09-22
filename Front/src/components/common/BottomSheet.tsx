import React, { ReactNode } from "react";

interface BottomSheetProps {
  children: ReactNode;
  closeSheet: () => void;
  size: number;
}

export const BottomSheet: React.FC<BottomSheetProps> = ({
  children,
  closeSheet,
}) => {

  return (
    <div className="fixed inset-0 h-screen items-center justify-center z-50 flex overflow-y-auto">
      <div
        className="bg-black opacity-50 absolute inset-0"
        onClick={closeSheet}
      ></div>
      <div className="flex justify-center">
        <div className="w-[30vw] rounded-full bg-gray h-4"></div>
      </div>
      <div
        className={`fixed bottom-0 h-[75vh] w-full mx-auto py-1 px-6 bg-white rounded-t-3xl shadow-lg z-10 `}
      >
        <div className="flex w-full justify-center bg-white h-14 items-center">
          <div className="fixed w-[30vw] rounded-full bg-gray h-4"></div>
        </div>
        <div className={"h-[75vh] overflow-y-auto p-2"}>{children}</div>
      </div>
    </div>
  );
};
