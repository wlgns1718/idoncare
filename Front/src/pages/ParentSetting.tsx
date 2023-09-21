import React from "react";
import Header from "../components/common/Header";
import Parent from "../components/common/Parent";
import ParentCheck from "../components/connect/ParentCheck";

const ParentSetting: React.FC = () => {
  return (
    <div className="flex flex-col justify-between h-screen">
      <div>
        <Header pageTitle="부모님" headerType="normal" headerLink="/" />

        <div className="mt-56">
          <div className="text-l text-center">내 부모님</div>
          <div className="m-5 flex">
            <Parent is_connect={true} pname="엄마" />
            <Parent is_connect={false} pname="아빠" />
          </div>
        </div>
      </div>

      <div className="mb-20">
        <ParentCheck name="김슬기" phoneNumber="010-1234-1234" />
      </div>
    </div>
  );
};

export default ParentSetting;
