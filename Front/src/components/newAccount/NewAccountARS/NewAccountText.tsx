type ArsTextIndex = number;
interface NewAccountTextProps {
  arsTextIndex: ArsTextIndex;
}

const NewAccountText = ({ arsTextIndex }: NewAccountTextProps) => {
  const text = [
    "금융실명법에 따라 금융(거래 정보 제공내역 문자(분기당 1회)를 위한 이메일이 필요합니다.)",
    "이메일을 등록하시면 오픈플랫폼에 회원가입되며, 오픈플랫폼 포털사이트에서 고객님의 핀테크 앱 접속 내역을 확인할 수 있습니다. 안내메일을 받으시면 비밀번호를 설정해 주세요.",
  ];
  return <p className="text-darkgray mb-[10px]">{text[arsTextIndex]}</p>;
};

export default NewAccountText;
