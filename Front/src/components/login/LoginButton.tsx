import { useEffect, useState } from "react";
import kakaoLogo from "../../assets/imgs/login/kakaoLogo.png";
import { useMutation } from "react-query";
import Loading from "../common/Loading";
import { kakaoLoginUrl } from "../../apis/url/kakaoLoginUrl";
import { PostLoginAxios } from "../../apis/axios/PostLoginAxios";
import { useSetRecoilState } from "recoil";
import { SignupCode } from "../../store/signup/atoms";
import { useNavigate } from "react-router-dom";
import { userData } from "../../store/common/atoms";

const LoginButton = () => {
  type AccessToken = string | null;
  type Email = string;
  type Joined = boolean;
  type Msg = string;
  type Nickname = string;
  type RefreshToken = string | null;
  type UserId = string;
  type Role = string;
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
    role: Role;
  }

  interface PostLogin {
    data: Data;
    error: Error;
    code: Code;
  }

  const navigate = useNavigate();
  const handleLogin = () => {
    window.location.href = kakaoLoginUrl;
  };
  const setKakaoCode = useSetRecoilState(SignupCode);
  const setUserInfo = useSetRecoilState(userData);

  useEffect(() => {
    if (window.location.search === "") {
      return;
    }
    setRedirect(true);
    mutate();
  }, []);

  const { mutate } = useMutation<PostLogin>(PostLoginAxios, {
    onSuccess: (res) => {
      if (res.code === 200) {
        setKakaoCode(res.data!.userId.toString());
        if (res.data?.joined === false) {
          navigate("/signup");
        } else {
          setUserInfo({
            nickname: res.data!.nickname,
            joined: true,
            userId: res.data!.userId,
            email: res.data!.email,
            refreshToken: res.data!.refreshToken,
            accessToken: res.data!.accessToken,
            role: res.data!.role,
          });
          console.log(res);
          navigate("/");
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
