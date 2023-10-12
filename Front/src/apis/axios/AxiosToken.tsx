import { useRecoilState } from "recoil";
import { userData } from "../../store/common/atoms";
import { useEffect } from "react";

interface AxiosTokenProps {
  token: string;
}

const AxiosToken = ({ token }: AxiosTokenProps) => {
  const [userInfo, setUserInfo] = useRecoilState(userData);

  useEffect(() => {
    setUserInfo(() => ({ ...userInfo, accessToken: token }));
  }, [token]);
};

export default AxiosToken;
