import accept from "../../../assets/imgs/newAccount/accept.png";
import circle from "../../../assets/imgs/newAccount/circle.png";

type Text = string;
type IsCheck = boolean;

interface NewAccountCheckBoxProps {
  text: Text;
  isCheck: IsCheck;
  onToggle: () => void;
}
const NewAccountCheckBox = ({ text, onToggle, isCheck }: NewAccountCheckBoxProps) => {
  return (
    <div className="mb-[10px]">
      <div
        className="w-full border-solid border-[3px] p-[10px] border-darkgray focus:border-black flex items-center"
        onClick={() => onToggle()}
      >
        {isCheck ? (
          <img src={accept} alt="accept img" className="w-[20px] h-[20px] mr-[10px]" />
        ) : (
          <img src={circle} alt="no accept img" className="w-[20px] h-[20px] mr-[10px]" />
        )}
        <p>{text}</p>
      </div>
    </div>
  );
};

export default NewAccountCheckBox;
