import { Link } from "react-router-dom";

type ButtonText = string;
type ButtonLink = string;

interface FullBtnProps {
  buttonText?: ButtonText;
  buttonLink?: ButtonLink;
  className?: string;
  isDone?: boolean;
}

const FullBtn: React.FC<FullBtnProps> = ({
  buttonText = "확인",
  buttonLink = "/",
  className = "",
  isDone = true,
}) => {
  const handleClick = (e) => {
    if (!isDone) e.preventDefault(); // isDone이 false인 경우: 링크 이동 X
  };

  return (
    <div className={`max-w-full ${className}`}>
      <Link to={buttonLink} onClick={handleClick}>
        <div className="bg-main p-3 rounded-xl text-center text-m mt-auto mb-6">
          <span className="text-white">{buttonText}</span>
        </div>
      </Link>
    </div>
  );
};

export default FullBtn;
