type First = string;
type Second = string;

interface NewAccountToggleButtonProps {
  first: First;
  second: Second;
}

const NewAccountToggleButton = ({ first, second }: NewAccountToggleButtonProps) => {
  return (
    <div className="flex w-[220px] h-[50px] ml-[10px]">
      <p
        className={`bg-main text-white border-main border-[3px] h-full px-[5px] py-[10px] w-[50%] text-center`}
      >
        {first}
      </p>
      <p
        className={`border-main border-solid border-[3px] text-darkgray h-full px-[5px] py-[10px] w-[50%] text-center`}
      >
        {second}
      </p>
    </div>
  );
};

export default NewAccountToggleButton;
