import React, { ReactElement } from "react";

export type ICON_NAME =
  | "home"
  | "search"
  | "total"
  | "purchase"
  | "settings"
  | "logout"
  | "user";

type ICON_SIZE = "small" | "medium" | "large";

const iconSizeObject = {
  small: {
    width: 6,
  },
  medium: {
    width: 8,
  },
  large: {
    width: 10,
  },
};
const iconPathObject: {
  [k in ICON_NAME]?: ReactElement;
} = {
  home: (
    <path
      strokeLinecap="round"
      strokeLinejoin="round"
      d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"
    />
  ),
  search: (
    <path
      strokeLinecap="round"
      strokeLinejoin="round"
      d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
    />
  ),
  total: (
    <path
      strokeLinecap="round"
      strokeLinejoin="round"
      d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"
    />
  ),
  purchase: (
    <path
      strokeLinecap="round"
      strokeLinejoin="round"
      d="M2.25 8.25h19.5M2.25 9h19.5m-16.5 5.25h6m-6 2.25h3m-3.75 3h15a2.25 2.25 0 002.25-2.25V6.75A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25v10.5A2.25 2.25 0 004.5 19.5z"
    />
  ),
};
interface IconProps {
  size?: ICON_SIZE;
  name?: ICON_NAME;
  className?: string;
}

export const Icon: React.FC<IconProps> = ({
  size = "medium",
  name = "home",
  className = "",
}) => {
  return (
    <div className={`w-[${iconSizeObject[size].width}vw] ${className}`}>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        strokeWidth={1.5}
        stroke="currentColor"
      >
        {iconPathObject[name]}
      </svg>
    </div>
  );
};

export default Icon;
