import phone from "../../../assets/imgs/newAccount/phone.png";

const NewAccountARSHelp = () => {
  return (
    <div className="flex flex-col items-center justify-center bg-soft py-[40px] text-l my-[10px]">
      <img src={phone} alt="phone" className="w-[100px] pb-[20px]" />
      <p className="text-darkgray">전화를 받으시고</p>
      <p className="text-main font-strong">주민등록상의 생년월일 6자리를</p>
      <p className="text-darkgray">입력해주세요.</p>
    </div>
  );
};

export default NewAccountARSHelp;
