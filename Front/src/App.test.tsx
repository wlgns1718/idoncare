import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import App from "./App";

describe("App", () => {
  test("App 컴포넌트 렌더링", () => {
    render(<App />);

    // assert
    const loginNav = screen.getByText(/login/i);
    const walletNav = screen.getByText(/wallet/i);
    const pocketMoneyNav = screen.getByText(/pocketMoney/i);

    expect(loginNav).toBeInTheDocument();
    expect(walletNav).toBeInTheDocument();
    expect(pocketMoneyNav).toBeInTheDocument();
  });
});
