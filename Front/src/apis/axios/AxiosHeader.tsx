type Token = string | null;

interface AxiosHeaderProps {
  token: Token;
}

export const AxiosHeader = ({ token }: AxiosHeaderProps) => {
  const axiosHeader = {
    headers: { Authorization: token as string, withCredentials: true },
  };
  return axiosHeader;
};

export default AxiosHeader;
