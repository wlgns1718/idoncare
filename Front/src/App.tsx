import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./pages/Login";
import PocketMoney from "./pages/PocketMoney";
import Wallet from "./pages/Wallet";
import Home from "./pages/Home";
import ChildReguestMoney from "./pages/DemandedMoney";
import SendPocketMoney from "./pages/PocketMoneySend";
import SendRegularMoney from "./pages/RegularMoneySend";
import Signup from "./pages/Signup";
import WalletSearch from "./pages/WalletSearch";
import { AppLayout } from "./layouts/AppLayout";
import WalletRecharge from "./pages/WalletRecharge";
import NewAccount from "./pages/NewAccount";
import Purchase from "./pages/Purchase";
import QRcodePurchase from "./pages/QRcodePurchase";
import CameraPurchase from "./pages/CameraPurchase";
import MyPage from "./pages/MyPage";
import RegistAccount from "./pages/RegistAccount";
import ARSPage from "./pages/ARSPage";
import RegistAgreement from "./pages/RegistAgreement";
import Report from "./pages/Report";
import Transfer from "./pages/Transfer";
import TransferSelect from "./pages/TransferSelect";
import TransferConfirm from "./pages/TransferConfirm";

function App() {
  return (
    <>
      <AppLayout>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="mypage" element={<MyPage />} />
            <Route path="wallet" element={<Wallet />} />
            <Route path="wallet/search" element={<WalletSearch />} />
            <Route path="wallet/recharge" element={<WalletRecharge />} />
            <Route path="wallet/recharge/regist" element={<RegistAccount />} />
            <Route
              path="wallet/recharge/regist/agreement"
              element={<RegistAgreement />}
            />
            <Route
              path="wallet/recharge/regist/agreement/ARS"
              element={<ARSPage />}
            />
            <Route path="report" element={<Report/>} />
            <Route path="purchase" element={<Purchase />} />
            <Route path="transfer" element={<Transfer />} />
            <Route path="transfer/account" element={<TransferSelect />} />
            <Route path="transfer/account/confirm" element={<TransferConfirm />} />
            <Route path="purchase/qrcode" element={<QRcodePurchase />} />
            <Route path="purchase/camera" element={<CameraPurchase />} />
            <Route path="pocketMoney" element={<PocketMoney />} />
            <Route path="childReguestMoney" element={<ChildReguestMoney />} />
            <Route path="sendPocketMoney" element={<SendPocketMoney />} />
            <Route path="sendRegularMoney" element={<SendRegularMoney />} />
            <Route path="signup" element={<Signup />} />
            <Route path="newAccount" element={<NewAccount />} />
          </Routes>
        </BrowserRouter>
      </AppLayout>
    </>
  );
}

export default App;
