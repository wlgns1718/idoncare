import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./pages/Login";
import PocketMoney from "./pages/PocketMoney";
import Wallet from "./pages/Wallet";
import Home from "./pages/Home";
import WalletSearch from "./pages/WalletSearch";
import { AppLayout } from "./layouts/AppLayout";
import BottomNav from "./components/common/BottomNav";
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
          </Routes>
        </BrowserRouter>
        <BottomNav />
      </AppLayout>
    </>
  );
}

export default App;
