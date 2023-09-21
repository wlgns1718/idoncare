import Header from "../components/common/Header";
import TransferMsg from './../components/wallet/TransferMsg';
import FullBtn from './../components/common/FullBtn';

function TransferConfirm() {
  return (
    <div>
      <Header pageTitle="보내기" />
      <TransferMsg/>
      <FullBtn buttonText="보내기"></FullBtn>
    </div>
  );
}

export default TransferConfirm;
