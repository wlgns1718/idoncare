import SignupName from "./SignupName";
// import SignupTypeSelect from "./SignupTypeSelect";

const SignupForm = () => {
  return (
    <div className="flex items-center justify-around h-full text-center text-s">
      {/* <SignupTypeSelect /> */}
      <SignupName />
    </div>
  );
};

export default SignupForm;
