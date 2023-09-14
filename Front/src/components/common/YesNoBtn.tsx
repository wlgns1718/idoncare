import { Link } from "react-router-dom";

type ButtonText = string;
type ButtonLink = string;

interface YesNoBtnProps {
  yesText?: ButtonText;
  noText?: ButtonText;
  yesLink?: ButtonLink;
  noLink?: ButtonLink;
  className?: string;
}

const YesNoBtn: React.FC<YesNoBtnProps> = ({
    yesText = "수락",
    noText = "거절",
    yesLink = "/",
    noLink = "-1",
    className="",
  }) => {
      return (
        <div className={`flex mt-auto ${className}`}>
        <div className="flex justify-between w-full mt-10">
          <Link to={noLink} className="block">
              {/* <div className="bg-gray text-black p-4 pl-16 pr-16 rounded-3xl inline-block mr-5 text-s"> */}
              <div className="bg-gray text-black p-4 w-44 rounded-3xl inline-block mr-5 text-s">
                  {noText}
              </div>
          </Link>
          <Link to={yesLink} className="block">
              <div className="bg-main text-white p-4 w-72 rounded-3xl inline-block text-s">
                  {yesText}
              </div>
          </Link>
        </div>
      </div>
      
      )
  }
  
export default YesNoBtn;
