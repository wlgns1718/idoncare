import React from 'react'
import { MissionData } from '../../pages/Misson'
import SmallBtn from './../pocketmoney/SmallBtn';

function MissionHistoryCard({mission} : {mission :MissionData}) {
  return (
    <div className='shadow-xl rounded-lg w-[30vw] h-[50vw] flex-col'>
      <div className=''>{mission.status}</div>
      <div>{mission.title}</div>
      <div>{mission.missionMoney}</div>
    </div>
  )
}

export default MissionHistoryCard