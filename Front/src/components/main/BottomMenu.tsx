import BottomMenuBox from './BottomMenuBox'

function BottomMenu() {
  return (
    <div className="mt-12">
      <BottomMenuBox link="/report" text="활동보고서"/>
      <BottomMenuBox link="/parentSetting" text="부모님 (임시)"/>
      <BottomMenuBox link="/kidSetting" text="자녀 관리 (임시)"/>
    </div>
  )
}

export default BottomMenu