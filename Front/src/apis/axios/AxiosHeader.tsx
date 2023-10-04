type Token = string | null;

interface AxiosHeaderProps {
  token: Token;
}

export const AxiosHeader = ({ token }: AxiosHeaderProps) => {
  const axiosHeader = {
    headers: { Authorization: "Bearer " + token as string },
  };
  return axiosHeader;
};

export default AxiosHeader;
