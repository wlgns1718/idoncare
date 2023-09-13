import { LayoutChildrenProps } from "../types/LayoutChildrenProps";

export const AppLayout = ({ children }: LayoutChildrenProps) => {
  return <div className="w-[calc(100vw-40px)]] h-[100vh] mx-[20px]">{children}</div>;
};
