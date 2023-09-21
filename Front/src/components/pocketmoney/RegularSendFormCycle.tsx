import { useState } from "react";
import Select from "react-select";
import YesNoBtn from "../common/YesNoBtn";

type SelectOption = {
  value: string;
  label: string;
};

interface Props {
  onSelected: (value: string) => void;
  onClose: () => void;
}

const RegularMoneySendFormCycle: React.FC<Props> = ({
  onSelected,
  onClose,
}) => {
  const [tempSelectedOption, setTempSelectedOption] =
    useState<SelectOption | null>(null);

  const frequencyOptions: SelectOption[] = [
    { value: "", label: "이체주기를 선택해주세요." },
    { value: "monthly", label: "매월" },
    { value: "weekly", label: "매주" },
    { value: "daily", label: "매일" },
  ];

  const handleOptionChange = (selectedOption: SelectOption | null) => {
    setTempSelectedOption(selectedOption);
  };

  const handleYesClick = () => {
    if (tempSelectedOption) {
      console.log(tempSelectedOption.label);
      onSelected(tempSelectedOption.label);
      onClose();
    }
  };

  return (
    <div>
      <Select
        options={frequencyOptions}
        value={tempSelectedOption}
        onChange={handleOptionChange}
        className="mt-10 mb-5 bg-gray-50 text-gray-900 text-sm rounded-lg focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
      />
      <YesNoBtn
        yesText="예"
        noText="아니오"
        onYesClick={handleYesClick}
        onNoClick={onClose}
      />
    </div>
  );
};

export default RegularMoneySendFormCycle;
