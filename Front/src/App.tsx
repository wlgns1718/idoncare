import { BrowserRouter, Route, Routes } from "react-router-dom";
import { RecoilRoot } from "recoil";
import { QueryClient, QueryClientProvider } from "react-query";
import Login from "./pages/Login";
import PocketMoney from "./pages/PocketMoney";
import Wallet from "./pages/Wallet";
import Home from "./pages/Home";
import DemandMoneyCheck from "./pages/DemandMoneyCheck";
import SendPocketMoney from "./pages/PocketMoneySend";
import SendRegularMoney from "./pages/RegularMoneySend";
import KidDemandMoney from "./pages/KidDemandMoney";
import KidDemandMoneyList from "./pages/KidDemandMoneyList";
import KidDemanedMoneyList from "./pages/KidDemanedMoneyList";
import KidRegister from "./pages/KidRegist";
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
import ParentSetting from "./pages/ParentSetting";
import KidSetting from "./pages/KidSetting";
import MissonPage from "./pages/MissonPage";
import MissionDetailPage from "./pages/MissionDetailPage";
import MissionCreatPage from "./pages/MissionCreatPage";

function App() {
  const queryClient = new QueryClient();
  return (
    <RecoilRoot>
      <AppLayout>
        <QueryClientProvider client={queryClient}>
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="login" element={<Login />} />
              <Route path="mypage" element={<MyPage />} />
              <Route path="wallet" element={<Wallet />} />
              <Route path="wallet/search" element={<WalletSearch />} />
              <Route path="wallet/recharge" element={<WalletRecharge />} />
              <Route
                path="wallet/recharge/regist"
                element={<RegistAccount />}
              />
              <Route
                path="wallet/recharge/regist/agreement"
                element={<RegistAgreement />}
              />
              <Route
                path="wallet/recharge/regist/agreement/ARS"
                element={<ARSPage />}
              />
              <Route path="report" element={<Report />} />
              <Route path="purchase" element={<Purchase />} />
              <Route path="purchase/qrcode" element={<QRcodePurchase />} />
              <Route path="purchase/camera" element={<CameraPurchase />} />
              <Route path="transfer/account" element={<TransferSelect />} />
              <Route path="transfer/input" element={<Transfer />} />
              <Route path="transfer/confirm" element={<TransferConfirm />} />
              <Route path="pocketMoney" element={<PocketMoney />} />
              <Route path="childReguestMoney" element={<DemandMoneyCheck />} />
              <Route path="sendPocketMoney" element={<SendPocketMoney />} />
              <Route path="sendRegularMoney" element={<SendRegularMoney />} />
              <Route path="kidDemandMoney" element={<KidDemandMoney />} />
              <Route
                path="kidDemandMoneyList"
                element={<KidDemandMoneyList />}
              />
              <Route
                path="kidDemanedMoneyList"
                element={<KidDemanedMoneyList />}
              />
              <Route path="kidRegister" element={<KidRegister />} />
              <Route
                path="kidDemandMoneyList"
                element={<KidDemandMoneyList />}
              />
              <Route
                path="kidDemanedMoneyList"
                element={<KidDemanedMoneyList />}
              />
              <Route path="signup" element={<Signup />} />
              <Route path="newAccount" element={<NewAccount />} />
              <Route path="parentSetting" element={<ParentSetting />} />
              <Route path="kidSetting" element={<KidSetting />} />
              <Route path="kidSetting" element={<KidSetting />} />
              <Route path="mission" element={<MissonPage />} />
              <Route path="mission/detail/:missionId" element={<MissionDetailPage />} />
              <Route path="mission/create" element={<MissionCreatPage />} />
            </Routes>
          </BrowserRouter>
        </QueryClientProvider>
      </AppLayout>
    </RecoilRoot>
  );
}

export default App;
