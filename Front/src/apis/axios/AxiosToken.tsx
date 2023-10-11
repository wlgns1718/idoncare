import { useRecoilState } from "recoil";
import { userData } from "../../store/common/atoms";
import { useEffect } from "react";

interface AxiosTokenProps {
  token: string;
}

const AxiosToken = ({ token }: AxiosTokenProps) => {
  console.log(token);
  const [userInfo, setUserInfo] = useRecoilState(userData);
  setUserInfo({ ...userInfo, accessToken: token });
  useEffect(() => {
    console.log(userInfo);
  }, [userInfo]);
};

export default AxiosToken;
