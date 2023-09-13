import { Link } from "react-router-dom";

type PageTitle = string;
type HeaderType = string;
type HeaderLink = string;
interface HeaderProps {
  pageTitle: PageTitle;
  headerType: HeaderType;
  headerLink: HeaderLink;
}

const Header = ({ pageTitle, headerType, headerLink }: HeaderProps) => {
  return (
    <ul className="flex items-center justify-between w-full">
      {headerType === "normal" ? (
        <>
          <li className={`text-x`}>
            <Link to={headerLink}>&lt;</Link>
          </li>
          <li className="text-l">{pageTitle}</li>
          <li className={`text-x text-transparent`}>X</li>
        </>
      ) : (
        <>
          <li className={`text-x text-transparent`}>&lt;</li>
          <li className={`text-x`}>
            <Link to={headerLink}>X</Link>
          </li>
        </>
      )}
    </ul>
  );
};

export default Header;
