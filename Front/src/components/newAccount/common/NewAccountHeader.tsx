type Step = number;

interface NewAccountHeaderProps {
  step: Step;
}

const NewAccountHeader = ({ step }: NewAccountHeaderProps) => {
  const text = [
    "아이돈케어의 앱 서비스를 사용하기 위한 고객님의 이용동의 및 본인인증 절차를 진행합니다.",
    "아이돈케어의 앱 서비스를 사용하기 위한 고객님의 출금/조회 등의 절차를 계좌소유인증으로 진행합니다.",
    "아이돈케어의 앱 서비스를 사용하기 위한 계좌소유인증(1원인증)을 통해 진행합니다.",
  ];
  return <p className="bg-light text-s p-[10px] mb-[20px]">{text[step - 1]}</p>;
};

export default NewAccountHeader;
