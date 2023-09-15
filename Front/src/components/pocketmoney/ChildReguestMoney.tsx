import { Link } from 'react-router-dom';

type ChildReguestMoneyProps = {
  name: string;
  amount: string;
};

const ChildReguestMoney: React.FC<ChildReguestMoneyProps> = ({ name, amount }) => {
  return (
    <div className="flex justify-between items-center text-s mb-8">
      <div className="flex items-center">
        <img src="/icons/icon-letter.png" alt="Icon" className="ml-2 mr-5 w-10 h-10"/>
        <Link to="/childReguestMoney">
          <div>{name}</div>
        </Link>
      </div>
      <div className="mr-3">{amount}원</div>
    </div>
  );
};

export default ChildReguestMoney;
