type Placeholder = string;

interface NewAccountInputProps {
  placeholder: Placeholder;
}

const NewAccountInput = ({ placeholder }: NewAccountInputProps) => {
  return (
    <input
      type="text"
      size={20}
      placeholder={placeholder}
      className="w-full border-solid border-[3px] p-[10px] border-darkgray"
    />
  );
};

export default NewAccountInput;
