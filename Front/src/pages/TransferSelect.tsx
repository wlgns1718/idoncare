import Header from "../components/common/Header";
import TransferSelectForm from "../components/wallet/TransferSelectForm";

function TransferSelect() {
  return (
    <div>
      <Header pageTitle="보내기" />
      <TransferSelectForm/>
    </div>
  );
}

export default TransferSelect;
