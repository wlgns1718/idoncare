import React from "react";
import { Link } from "react-router-dom";
import Header from "../components/common/Header";

const Login = () => {
  return (
    <>
      <Header pageTitle="로그인" headerType="normal" />
      <Link to="/">Login</Link>
    </>
  );
};

export default Login;
