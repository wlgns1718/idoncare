import React, { useState } from "react";
import Header from "../components/common/Header";
import SearchForm from "../components/wallet/SearchForm";

const WalletSearch = () => {
  const [searchKeyword, setSearchKeyword] = useState("");

  const handdleSearchKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchKeyword(e.target.value);
    console.log(searchKeyword);
  };
  const resetSearchKeyword = () => {
    setSearchKeyword("");
  };

  return (
    <div>
      <Header pageTitle="거래내역 검색" />
      <SearchForm
        searchKeyword={searchKeyword}
        onChange={handdleSearchKeyword}
        resetKeyword={resetSearchKeyword}
        className={"m-5"}
      />
    </div>
  );
};

export default WalletSearch;
