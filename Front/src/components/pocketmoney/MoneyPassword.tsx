import React, { useState } from 'react';
import PasswordForm from '../common/PasswordForm';
import FullBtn from '../common/FullBtn';

interface Props {
  onNext?: () => void;
}

const ParentComponent: React.FC<Props> = ({ onNext }) => {
  const [password, setPassword] = useState('');

  return (
    <div>
      <PasswordForm password={password} handleSetPassword={setPassword} />
      <FullBtn onClick={() => { 
                if(password.length === 6){
                    if(onNext) onNext();
                    console.log(password);
                } else{
                    alert("비밀번호는 반드시 숫자 여섯 자리여야 합니다.");
                }
            }}/>
    </div>
  );
};

export default ParentComponent;
