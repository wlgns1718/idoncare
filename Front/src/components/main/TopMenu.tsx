import TopMenuBox from '../main/TopMenuBox'
import TopAccount from './TopAccount'

function TopMenu() {
    return (
      <div className="bg-main rounded-b-3xl flex flex-col items-center"
      style={{ height: '330px', borderBottomLeftRadius: '60px', borderBottomRightRadius: '60px' }}>
          <div>
              <img src="/icons/icon-logo-2.png" alt="logo" className="w-36 h-28 p-3 mt-2"/>
          </div>

          <TopAccount />
          
          <div className="flex justify-between">
              <TopMenuBox link="/pocketMoney" bgColor="gray" text="용돈" />
              <TopMenuBox link="/" bgColor="gray" text="미션" />
          </div>
  
      </div>
    )
  }
  

export default TopMenu