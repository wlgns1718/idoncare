import React from "react";
import { Link } from "react-router-dom";

const Wallet = () => {
  return (
    <div>
      <p>Wallet</p>
      <div className="bg-yellow-100">
        <Link to="/">Login</Link>
      </div>
    </div>
  );
};

export default Wallet;
