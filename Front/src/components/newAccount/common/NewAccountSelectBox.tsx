import { MobileSort } from "../../../store/newAccount/atoms";

type Step = number;

interface NewAccountSelectBoxProps {
  step: Step;
  changeValue : (value: MobileSort) => void;
}

const NewAccountSelectBox = ({
  step,
  changeValue,
}: NewAccountSelectBoxProps) => {
  const data = [
    {
      value: ["이동통신사를 선택하세요", "SKT", "KT", "LG"],
    },
  ];
  return (
    <select
      name="telecom h-[50px]"
      className="w-[100%] p-[10px] border-solid border-[3px] border-darkgray mb-[10px]"
      onChange={()=> changeValue("LG")}
    >
      {data[step - 1].value.map((value) => (
        <option value={value} key={value}>{value}</option>
      ))}
    </select>
  );
};

export default NewAccountSelectBox;
