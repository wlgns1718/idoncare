import React, { useState } from 'react';
import Number from '../pocketmoney/Number';

const KidPhoneForm: React.FC = () => {
  const [inputValue, setInputValue] = useState('');

  const handleNumberClick = (num: number | string) => {
    if (typeof num === 'number') {
      setInputValue((prevState) => {
        const numericValue = prevState.replace(/[^0-9]/g, '');

        if (numericValue.length < 11) {
          return numericValue + num.toString();
        }

        return prevState;
      });
    }
  };

  const handleClear = () => {
    setInputValue('');
  };

  const handleBackspace = () => {
    setInputValue((prevState) => {
      return prevState.slice(0, -1);
    });
  };

  // 형식 맞추기
  const formatInputValue = (value: string) => {
    const numericValue = value.replace(/[^0-9]/g, '');
    if (numericValue.length >= 4 && numericValue.length < 7) {
      return `${numericValue.slice(0, 3)}-${numericValue.slice(3)}`;
    } else if (numericValue.length >= 7) {
      return `${numericValue.slice(0, 3)}-${numericValue.slice(3, 7)}-${numericValue.slice(7)}`;
    }
    return numericValue;
  };

  return (
    <div>
      <div className="text-l text-center text-main font-strong mb-24">
        {inputValue ? formatInputValue(inputValue) : '　'}
      </div>

      <Number
        bottomLeftText="CLR"
        bottomRightText="<-"
        onNumberClick={handleNumberClick}
        onLeft={handleClear}
        onRight={handleBackspace}
      />
    </div>
  );
};

export default KidPhoneForm;