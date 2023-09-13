import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div>
      <div className="flex flex-col bg-red-100">
        <Link to="/login">login</Link>
        <Link to="/wallet">wallet</Link>
        <Link to="/pocketMoney">pocketMoney</Link>
      </div>
    </div>
  );
};

export default Home;
