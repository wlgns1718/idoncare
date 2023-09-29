// import { useState } from "react";
// import Select from "react-select";
// import YesNoBtn from "../common/YesNoBtn";

// type SelectOption = {
//   value: string;
//   label: string;
// };

// interface Props {
//   onSelected: (value: string) => void;
//   onClose: () => void;
// }

// const RegularMoneySendFormCycle: React.FC<Props> = ({
//   onSelected,
//   onClose,
// }) => {
//   const [tempSelectedOption, setTempSelectedOption] =
//     useState<SelectOption | null>(null);

//   const frequencyOptions: SelectOption[] = [
//     { value: "", label: "이체주기를 선택해주세요." },
//     { value: "monthly", label: "매월" },
//     { value: "weekly", label: "매주" },
//     { value: "daily", label: "매일" },
//   ];

//   const handleOptionChange = (selectedOption: SelectOption | null) => {
//     setTempSelectedOption(selectedOption);
//   };

//   const handleYesClick = () => {
//     if (tempSelectedOption) {
//       console.log(tempSelectedOption.label);
//       onSelected(tempSelectedOption.label);
//       onClose();
//     }
//   };

//   return (
//     <div>
//       <Select
//         options={frequencyOptions}
//         value={tempSelectedOption}
//         onChange={handleOptionChange}
//         className="mt-10 mb-5 bg-gray-50 text-gray-900 text-sm rounded-lg focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
//       />
//       <YesNoBtn
//         yesText="예"
//         noText="아니오"
//         onYesClick={handleYesClick}
//         onNoClick={onClose}
//       />
//     </div>
//   );
// };

// export default RegularMoneySendFormCycle;

import React, { useState } from "react";
import Header from "../common/Header";
import FullBtn from "../common/FullBtn";

interface Props {
  onNext: (type: string, cycle: number) => void;
}

const RegularSendFormCycle: React.FC<Props> = ({ onNext }) => {
  const [type, setType] = useState<string | null>(null);
  const [cycle, setCycle] = useState<number | null>(null);

  const handleTypeChange = (selectedType: string) => {
    setType(selectedType);
    setCycle(null);
  
    if (selectedType === 'DAY') {
      setCycle(1);
    }
  };
  

  const handleCycleChange = (selectedCycle: number) => {
    setCycle(selectedCycle);
  };

  const handleNextClick = () => {
    if(type && cycle){
      onNext(type, cycle);
    }
  };

  return (
    <div className="flex flex-col h-screen pb-60">
      <Header pageTitle="정기용돈 등록" headerType="normal" headerLink="/" />
      <div className="m-10 text-center flex-grow">
        {/* Type 및 Cycle 선택 UI 구현 */}
<select onChange={e => handleTypeChange(e.target.value)}>
  <option value="">선택하세요</option>
  <option value="DAY">DAY</option>
  <option value="WEEK">WEEK</option>
  <option value="MONTH">MONTH</option>
</select>

{type === 'WEEK' && (
  <select onChange={e => handleCycleChange(Number(e.target.value))}>
    {[...Array(7)].map((_, i) => (
      <option key={i} value={i + 1}>{i + 1}</option>
    ))}
  </select>
)}

{type === 'MONTH' && (
  <select onChange={e => handleCycleChange(Number(e.target.value))}>
    {[...Array(31)].map((_, i) => (
      <option key={i} value={i + 1}>{i + 1}</option>
    ))}
   </select> 
)}

        
      </div>

      <FullBtn buttonText="다음" onClick={handleNextClick} isDone={!!(type && cycle)} />
    </div>
  );
};

export default RegularSendFormCycle;
