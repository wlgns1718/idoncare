import React, { useState } from 'react';
import Select from 'react-select';

function RegularMoneySendFormCycle() {
  const [firstOption, setFirstOption] = useState("");

  const frequencyOptions = [
    { value: "", label: "이체주기를 선택해주세요." },
    { value: "monthly", label: "매월" },
    { value: "weekly", label: "매주" },
    { value: "daily", label: "매일" }
  ];

  const dayOptions = [
    ...['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'].map(day => ({ value: day, label: day })),
    ...Array.from({length: 31}, (_, i) => i + 1).map(date => ({value:`day${date}`,label:`${date}`})),
  ];

  
  const handleFirstOptionChange = (selectedOption) => {
    setFirstOption(selectedOption);
  };

  
const filteredDayOptions = firstOption.value === "" ? [] : 
                           firstOption.value === "daily" ? [{value:"Daily",label:"Daily"}] : 
                           firstOption.value === "weekly" ? dayOptions.slice(0,7) : 
                           dayOptions.slice(7);

return (
    <>
    <Select 
    options={frequencyOptions}
    value={firstOption}
    onChange={handleFirstOptionChange}
    className="mt-10 mb-5 bg-gray-50 text-gray-900 text-sm rounded-lg focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
    />

    <Select
    options={filteredDayOptions}
    className="mb-2 bg-gray-50 text-gray-900 text-sm rounded-lg focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
    />
    </>
);
}

export default RegularMoneySendFormCycle;
