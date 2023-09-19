import { useState } from "react";

type Placeholder = string;

interface NewAccountInputProps {
  placeholder: Placeholder;
}

const NewAccountInput = ({ placeholder }: NewAccountInputProps) => {
  const [inputText, setInputText] = useState("");
  return (
    <input
      type="text"
      size={20}
      placeholder={placeholder}
      className="w-full border-solid border-[3px] p-[10px] border-darkgray mb-[10px]"
      value={inputText}
      onChange={(e) => setInputText(e.target.value)}
    />
  );
};

export default NewAccountInput;
