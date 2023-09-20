import Header from "../components/common/Header";
import SignupForm from "../components/signup/SignupForm";

const Signup = () => {
  return (
    <>
      <Header pageTitle="회원가입" headerType="normal" headerLink="/" />
      <SignupForm />
    </>
  );
};

export default Signup;
