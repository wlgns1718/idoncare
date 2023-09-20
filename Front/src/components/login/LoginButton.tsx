import kakaoLogo from "../../assets/imgs/login/kakaoLogo.png";
const LoginButton = () => {
  const REST_API_KEY = import.meta.env.VITE_REST_API_KEY;
  const REDIRECT_URI = "http://127.0.0.1:8000"; //Redirect URI
  // oauth 요청 URL
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code&prompt=select_account`;

  const handleLogin = () => {
    window.location.href = kakaoURL;
  };
  return (
    <button
      className="w-full bg-[#fae100] rounded-[10px] h-[60px] flex justify-center items-center mt-[100px]"
      onClick={handleLogin}
    >
      <img className="w-[20px] mx-[2px] mt-[4px]" src={kakaoLogo} />
      <p className="text-m mx-[2px]">카카오 로그인</p>
    </button>
  );
};

export default LoginButton;
