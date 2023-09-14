import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./pages/Login";
import PocketMoney from "./pages/PocketMoney";
import Wallet from "./pages/Wallet";
import Home from "./pages/Home";
import { AppLayout } from "./layouts/AppLayout";
import Signup from "./pages/Signup";

function App() {
  return (
    <>
      <AppLayout>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="wallet" element={<Wallet />} />
            <Route path="pocketMoney" element={<PocketMoney />} />
            <Route path="signup" element={<Signup />} />
          </Routes>
        </BrowserRouter>
      </AppLayout>
    </>
  );
}

export default App;
