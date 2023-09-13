import { Link } from "react-router-dom";

const Login = () => {
  return (
    <div>
      <p>Login</p>
      <div className="flex flex-col bg-red-100">
        <Link to="main">main</Link>
        <Link to="wallet">wallet</Link>
        <Link to="pocketMoney">pocketMoney</Link>
      </div>
    </div>
  );
};

export default Login;
