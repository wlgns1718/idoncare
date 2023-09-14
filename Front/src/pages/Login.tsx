import Header from "../components/common/Header";
import LoginLogo from "../components/login/LoginLogo";
import LoginButton from "../components/login/LoginButton";

const Login = () => {
  return (
    <>
      <Header pageTitle="로그인" headerType="normal" headerLink="/" />
      <LoginLogo />
      <LoginButton />
    </>
  );
};

export default Login;
