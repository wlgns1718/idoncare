import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "./pages/Main";
import Login from "./pages/Login";
import PocketMoney from "./pages/PocketMoney";
import Wallet from "./pages/Wallet";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="main" element={<Main />} />
          <Route path="wallet" element={<Wallet />} />
          <Route path="pocketMoney" element={<PocketMoney />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
