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
      },
      fontSize: {
        s: "1.2rem",
        m: "1.6rem",
        l: "2rem",
        x: "2.4rem",
      },
      fontFamily: {
        main: ["main"],
        strong: ["strong"],
      },
    },
  },
  plugins: [],
};
