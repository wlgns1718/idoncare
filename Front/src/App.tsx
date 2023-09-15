import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./pages/Login";
import PocketMoney from "./pages/PocketMoney";
import Wallet from "./pages/Wallet";
import Home from "./pages/Home";
import ChildReguestMoney from "./pages/ChildReguestMoney";
import MoneySendDone from "./pages/MoneySendDone";
import SendPocketMoney from "./pages/SendPocketMoney";
import SendPocketMoneyMsg from "./pages/SendPocketMoneyMsg";
import SendRegularMoney from "./pages/SendRegularMoney";
import RegularMoneyDone from "./pages/RegularMoneyDone";
import ReguestedMoney from "./pages/ReguestedMoney";
import Signup from "./pages/Signup";
import WalletSearch from "./pages/WalletSearch";
import { AppLayout } from "./layouts/AppLayout";
import BottomNav from "./components/common/BottomNav";
import WalletRecharge from "./pages/WalletRecharge";
import NewAccount from "./pages/NewAccount";

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
            <Route path="sendRegularMoney" element={<SendRegularMoney />} />
            <Route path="regularMoneyDone" element={<RegularMoneyDone />} />
            <Route path="reguestedMoney" element={<ReguestedMoney />} />
            <Route path="signup" element={<Signup />} />
            <Route path="newAccount" element={<NewAccount />} />
          </Routes>
        </BrowserRouter>
        <BottomNav />
      </AppLayout>
    </>
  );
}

export default App;
