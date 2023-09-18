type Step = number;

interface NewAccountHeaderProps {
  step: Step;
}

const NewAccountHeader = ({ step }: NewAccountHeaderProps) => {
  const text = ["오픈뱅킹 서비스를 사용하기 위한 고객님의 이용동의 및 본인인증 절차를 진행합니다."];
  return <p className="bg-light text-s p-[10px] mb-[20px]">{text[step - 1]}</p>;
};

export default NewAccountHeader;
