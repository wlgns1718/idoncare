import React from 'react'
import Header from "../components/common/Header";
import Parent from "../components/common/Parent";

const ParentSetting: React.FC = () => {
  return (
    <div>
        <Header pageTitle="부모님" headerType="normal" headerLink="/" />

        <div className="m-10 flex mt-64">
            <Parent is_connect={true}/>
            <Parent is_connect={false} />
        </div>
    </div>
  )
}

export default ParentSetting
