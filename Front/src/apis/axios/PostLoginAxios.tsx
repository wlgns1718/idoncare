import axios from "axios";
import { baseUrl } from "../url/baseUrl";
import { useSetRecoilState } from "recoil";
import { SignupCode } from "../../store/signup/atoms";
// API 호출
export const PostLoginAxios = async () => {
  const REDIRECT_URI = `${window.location.origin}/login`;
  const params = new URLSearchParams(window.location.search);
  const code = params.get("code");
  const setKakaoCode = useSetRecoilState(SignupCode);
  setKakaoCode(code);
  const path = "api/user/login";
  const payload = { code, redirectUrl: REDIRECT_URI };

  const result = await axios.post(baseUrl + path, payload);

  return result.data;
};
