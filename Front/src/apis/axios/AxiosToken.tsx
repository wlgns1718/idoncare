import { useRecoilState } from "recoil";
import { userData } from "../../store/common/atoms";
import { useEffect } from "react";

interface AxiosTokenProps {
  token: string;
}

const AxiosToken = ({ token }: AxiosTokenProps) => {
  const [userInfo, setUserInfo] = useRecoilState(userData);
  console.log(userInfo);
  useEffect(() => {
    setUserInfo((currentUserInfo) => ({ ...currentUserInfo, accessToken: token }));
  }, [token]);

  return null;
};

export default AxiosToken;
