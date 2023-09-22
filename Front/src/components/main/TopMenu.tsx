import TopMenuBox from "../main/TopMenuBox";
import WalletCard from "../wallet/WalletCard";

function TopMenu() {
  return (
    <div
      className="bg-main rounded-b-3xl flex flex-col items-center px-6 pb-10"
      style={{
        borderBottomLeftRadius: "60px",
        borderBottomRightRadius: "60px",
      }}
    >
      <div>
        <img
          src="/icons/icon-logo-2.png"
          alt="logo"
          className="w-36 h-28 p-3 mt-2"
        />
      </div>
      <div className="w-full">
        <div className="w-full">
          <WalletCard />
        </div>
        <div className="flex justify-between w-full gap-4 pb-4">
          <TopMenuBox link="/pocketMoney" bgColor="gray" text="용돈" />
          <TopMenuBox link="/" bgColor="gray" text="미션" />
        </div>
      </div>
    </div>
  );
}

export default TopMenu;
