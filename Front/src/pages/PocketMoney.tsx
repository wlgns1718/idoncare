import React from "react";
import { Link } from "react-router-dom";

const PocketMoney = () => {
  return (
    <div>
      <p>PocketMoney</p>
      <div className="bg-green-100">
        <Link to="/">Login</Link>
      </div>
    </div>
  );
};

export default PocketMoney;
