import NewAccountCreate from "../components/newAccount/NewAccountCreate";
import Header from "../components/common/Header";

const NewAccount = () => {
  return (
    <>
      <Header pageTitle="오픈뱅킹" headerType="normal" headerLink="/" />
      <NewAccountCreate />
    </>
  );
};

export default NewAccount;
