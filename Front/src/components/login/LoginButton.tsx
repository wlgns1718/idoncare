import { useEffect, useState } from "react";
import kakaoLogo from "../../assets/imgs/login/kakaoLogo.png";
import { useMutation } from "react-query";
import Loading from "../common/Loading";
import { kakaoLoginUrl } from "../../apis/url/kakaoLoginUrl";
import { PostLoginAxios } from "../../apis/axios/PostLoginAxios";
const LoginButton = () => {
  type AccessToken = string | null;
  type Email = string;
  type Joined = boolean;
  type Msg = string;
  type Nickname = string;
  type RefreshToken = string | null;
  type UserId = number;
  type Data = null | PostLoginData;
  type Error = string | null;
  type Code = number;

  interface PostLoginData {
    accessToken: AccessToken;
    email: Email;
    joined: Joined;
    msg: Msg;
    nickname: Nickname;
    refreshToken: RefreshToken;
    userId: UserId;
  }

  interface PostLogin {
    data: Data;
    error: Error;
    code: Code;
  }

  const handleLogin = () => {
    window.location.href = kakaoLoginUrl;
  };

  useEffect(() => {
    if (window.location.search === "") {
      return;
    }
    setRedirect(true);
    mutate();
  }, []);

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const { mutate } = useMutation<PostLogin>(PostLoginAxios, {
    onSuccess: (res) => {
      if (res.code === 200) {
        if (res.data?.joined === false) {
          window.location.href = "/signup";
        } else {
          window.location.href = "/";
        }
      } else {
        alert("Error: " + res.code + " " + res.error);
      }

      console.log(res);
    },
  });

  const [redirect, setRedirect] = useState(false);

  return (
    <>
      {!redirect ? (
        <button
          className="w-full bg-[#fae100] rounded-[10px] h-[60px] flex justify-center items-center mt-[100px]"
          onClick={handleLogin}
        >
          <img className="w-[20px] mx-[2px] mt-[4px]" src={kakaoLogo} />
          <p className="text-m mx-[2px]">카카오 로그인</p>
        </button>
      ) : (
        <div className="flex items-center justify-center font-strong">
          <Loading />
        </div>
      )}
    </>
  );
};

export default LoginButton;
