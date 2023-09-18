import { Link } from "react-router-dom";

type ButtonText = string;
type ButtonLink = string;

interface YesNoBtnProps {
  yesText?: ButtonText;
  noText?: ButtonText;
  yesLink?: ButtonLink;
  noLink?: ButtonLink;
  className?: string;
  onYesClick?: () => void; 
  onNoClick?: () => void; 
}

const YesNoBtn: React.FC<YesNoBtnProps> = ({
    yesText = "수락",
    noText = "거절",
    yesLink = "/",
    noLink = "/",
    className="",
    onYesClick,
    onNoClick,
}) => {

const handleYesClick = (e) => {
    if (yesLink === "" && onYesClick) { 
    e.preventDefault();
    onYesClick();
    }
    };

const handleNoClick = (e) => { 
    if (noLink === "" && onNoClick) {
    e.preventDefault();
    onNoClick();
    }
    };

    return (
        <div className={`flex mt-auto ${className}`}>
          <div className="flex justify-between w-full mt-10">
            <Link to={noLink} onClick={handleNoClick} className={`block ${className}`}>
              <div className="bg-mediumgray text-black p-4 rounded-3xl inline-block mr-5 text-s w-48">
                {noText}
              </div>
            </Link>
            <Link to={yesLink} onClick={handleYesClick} className={`block ${className}`}>
              <div className="bg-main text-white p-4 rounded-3xl inline-block text-s w-48">
                {yesText}
              </div>
            </Link>
          </div>
        </div>
      )
      
    }

export default YesNoBtn;
