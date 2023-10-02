import { Link } from "react-router-dom";
import TopMenu from "../components/main/TopMenu";
import BottomMenu from "../components/main/BottomMenu";

const Home = () => {
  return (
    <div>
      <div className="flex flex-col bg-red-100">
        <Link to="/login">login</Link>
      </div>
      <TopMenu />
      <div className="mx-8">
        <BottomMenu />
      </div>
    </div>
  );
};

export default Home;
