import { useRecoilState } from "recoil";
import { userData } from "../../store/common/atoms";

interface AxiosTokenProps {
  token: string;
}

const AxiosToken = ({ token }: AxiosTokenProps) => {
  const [userInfo, setUserInfo] = useRecoilState(userData);

  if (token === undefined) {
    token = userInfo.accessToken!;
  }

  setUserInfo({ ...userInfo, accessToken: token });
};

export default AxiosToken;
