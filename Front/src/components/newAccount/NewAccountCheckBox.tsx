import accept from "../../assets/imgs/newAccount/accept.png";
import circle from "../../assets/imgs/newAccount/circle.png";

type Text = string;
type IsAccept = boolean;

interface NewAccountCheckBoxProps {
  text: Text;
  isAccept: IsAccept;
}
const NewAccountCheckBox = ({ text, isAccept }: NewAccountCheckBoxProps) => {
  return (
    <div className="w-full border-solid border-[3px] p-[10px] border-darkgray focus:border-black flex items-center">
      {isAccept ? (
        <img src={accept} alt="accept img" className="w-[20px] h-[20px] mr-[10px]" />
      ) : (
        <img src={circle} alt="no accept img" className="w-[20px] h-[20px] mr-[10px]" />
      )}
      <p>{text}</p>
    </div>
  );
};

export default NewAccountCheckBox;
