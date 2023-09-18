type Step = number;

interface NewAccountSelectBoxProps {
  step: Step;
}

const NewAccountSelectBox = ({ step }: NewAccountSelectBoxProps) => {
  const data = [
    {
      value: ["이동통신사를 선택하세요", "SKT", "KT", "LG"],
    },
    {
      value: ["은행을 선택하세요", "대구은행", "농협", "국민은행", "신한은행"],
    },
  ];
  return (
    <select
      name="telecom h-[50px]"
      className="w-[100%] p-[10px] border-solid border-[3px] border-darkgray mb-[10px]"
    >
      {data[step - 1].value.map((value) => (
        <option value={value}>{value}</option>
      ))}
    </select>
  );
};

export default NewAccountSelectBox;
