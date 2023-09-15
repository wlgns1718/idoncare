/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        light: "#E1E8FA",
        main: "#2479E0",
        thick: "#1C51AD",
        white: "#FFFFFF",
        gray: "#F0F0F0",
        darkgray: "#666666",
        black: "#000000",
        // 임시 추가
        yellow: "#F4D160",
        sky: "#75C2F6",
      },
      fontSize: {
        t: "1.2rem",
        s: "1.4rem",
        m: "1.6rem",
        l: "2rem",
        x: "2.4rem",
        // 임시 추가
      },
      fontFamily: {
        main: ["main"],
        strong: ["strong"],
      },
    },
  },
  plugins: [],
};
