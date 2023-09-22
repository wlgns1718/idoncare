import { useEffect } from "react";
import kakaoLogo from "../../assets/imgs/login/kakaoLogo.png";
import axios from "axios";
import { baseUrl } from "../../apis/url/baseUrl";
import { useMutation } from "react-query";
const LoginButton = () => {
  const REST_API_KEY = import.meta.env.VITE_REST_API_KEY;
  const REDIRECT_URI = "http://localhost:5173/login"; //Redirect URIz
  // oauth 요청 URL
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code&prompt=select_account`;

  const handleLogin = () => {
    window.location.href = kakaoURL;
  };

  useEffect(() => {
    mutate();
  }, []);

  const checkUserInfo = async () => {
    const params = new URLSearchParams(window.location.search);
    const code = params.get("code");
    const path = "api/user/login";
    const payload = { code };

    if (code) {
      axios
        .post(baseUrl + path, payload)
        .then((res) => console.log(res.data))
        .catch((err) => console.log(err));
    }
  };

  const { mutate, isLoading } = useMutation(checkUserInfo, {
    onSuccess: (data) => {
      console.log(data);
    },
    onError: (err) => {
      console.log(err);
    },
  });
  return (
    <>
      {!isLoading && (
        <button
          className="w-full bg-[#fae100] rounded-[10px] h-[60px] flex justify-center items-center mt-[100px]"
          onClick={handleLogin}
        >
          <img className="w-[20px] mx-[2px] mt-[4px]" src={kakaoLogo} />
          <p className="text-m mx-[2px]">카카오 로그인</p>
        </button>
      )}
    </>
  );
};

export default LoginButton;
