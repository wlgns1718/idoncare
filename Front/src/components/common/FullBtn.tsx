import { Link } from "react-router-dom";

type ButtonText = string;
type ButtonLink = string;

interface FullBtnProps {
  buttonText?: ButtonText;
  buttonLink?: ButtonLink;
  className?: string;
}

const FullBtn: React.FC<FullBtnProps> = ({
  buttonText = "확인",
  buttonLink = "/",
  className = "",
}) => {
  return (
    <Link to={buttonLink}>
      <div
        className={`max-w-full bg-main p-3 rounded-xl text-center text-m mt-auto mb-6 ${className}`}
      >
        <span className="text-white">{buttonText}</span>
      </div>
    </Link>
  );
};

export default FullBtn;
