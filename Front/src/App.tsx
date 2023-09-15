import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./pages/Login";
import PocketMoney from "./pages/PocketMoney";
import Wallet from "./pages/Wallet";
import Home from "./pages/Home";
import ChildReguestMoney from "./pages/ChildReguestMoney";
import MoneySendDone from "./pages/MoneySendDone";
import SendPocketMoney from "./pages/SendPocketMoney";
import SendPocketMoneyMsg from "./pages/SendPocketMoneyMsg";
import Signup from "./pages/Signup";
import WalletSearch from "./pages/WalletSearch";
import { AppLayout } from "./layouts/AppLayout";
import WalletRecharge from "./pages/WalletRecharge";

function App() {
  return (
    <>
      <AppLayout>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="wallet" element={<Wallet />} />
            <Route path="wallet/search" element={<WalletSearch />} />
            <Route path="wallet/recharge" element={<WalletRecharge />} />
            <Route path="pocketMoney" element={<PocketMoney />} />
            <Route path="childReguestMoney" element={<ChildReguestMoney />} />
            <Route path="moneySendDone" element={<MoneySendDone />} />
            <Route path="sendPocketMoney" element={<SendPocketMoney />} />
            <Route path="sendPocketMoneyMsg" element={<SendPocketMoneyMsg />} />
            <Route path="signup" element={<Signup />} />
          </Routes>
        </BrowserRouter>
      </AppLayout>
    </>
  );
}

export default App;
