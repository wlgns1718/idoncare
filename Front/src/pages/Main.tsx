import React from "react";
import { Link } from "react-router-dom";

const Main = () => {
  return (
    <div>
      <p>main</p>
      <div className="bg-blue-100">
        <Link to="/">Login</Link>
      </div>
    </div>
  );
};

export default Main;
