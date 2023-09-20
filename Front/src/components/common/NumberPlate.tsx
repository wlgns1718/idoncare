import React from 'react';

type ButtonText = string;

interface NumberPlateProps {
  bottomLeftText?: ButtonText;
  bottomRightText?: ButtonText;
}

const NumberPlate: React.FC<NumberPlateProps> = ({
  bottomLeftText = "",
  bottomRightText = "<-",
}) => {
  const numbers = [
    [1,2,3],
    [4,5,6],
    [7,8,9],
    [bottomLeftText,0,bottomRightText]
  ];

  return (
    <div className="grid grid-cols-3 gap-6 mb-10 mt-5">
      {numbers.map((row,i) => 
        row.map((num,j) =>
          <div key={i*3+j} className="p-2 text-l">
            {num}
          </div>
        )
      )}
    </div>
  );
};

export default NumberPlate;
