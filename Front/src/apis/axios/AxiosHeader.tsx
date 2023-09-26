type Token = string;

interface AxiosHeaderProps {
  token: Token;
}

export const AxiosHeader = ({ token }: AxiosHeaderProps) => {
  const axiosHeader = {
    headers: { Authorization: token as string },
  };
  return axiosHeader;
};

export default AxiosHeader;
