type PageTitle = string;
type HeaderType = string;
interface HeaderProps {
  pageTitle: PageTitle;
  headerType: HeaderType;
}

const Header = ({ pageTitle, headerType }: HeaderProps) => {
  return (
    <ul className="flex items-center justify-between w-full">
      {headerType !== "normal" ? (
        <li className={`text-x`}>&lt;</li>
      ) : (
        <li className={`text-x text-transparent`}>&lt;</li>
      )}
      <li className="text-l">{pageTitle}</li>
      <li className="text-x">X</li>
    </ul>
  );
};

export default Header;
