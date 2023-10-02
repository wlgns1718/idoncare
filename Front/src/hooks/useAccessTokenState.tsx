import { useRecoilValue } from "recoil";
import { userToken } from "../store/common/atoms";

const useAccessTokenState = () => {
  const accessToken = useRecoilValue(userToken);
  return { accessToken };
};

export default useAccessTokenState;
