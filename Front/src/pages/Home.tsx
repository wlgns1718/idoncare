import { Link } from "react-router-dom";
import TopMenu from "../components/main/TopMenu";
import BottomMenu from "../components/main/BottomMenu"; 

const Home = () => {
  return (
    <div>
      <div className="flex flex-col bg-red-100">
        <Link to="/login">login</Link>
        <Link to="/wallet">wallet</Link>
        <Link to="/pocketMoney">pocketMoney</Link>      
      </div>

      <div>
        <TopMenu />
        <BottomMenu />
      </div>

    </div>
  );
};

export default Home;
