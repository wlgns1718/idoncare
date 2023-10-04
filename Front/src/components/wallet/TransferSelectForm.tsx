import { useState } from "react";
import FullBtn from "../common/FullBtn";
import AccountSelectForm from "./AccountSelectForm";
import RechargeAccountList from "./RechargeAccountList";

type SendOption = "family" | "account";

interface SendOptionS {
  value: SendOption;
  label: string;
}

function TransferSelectForm() {
  const [sendOption, setSendOption] = useState<SendOption>("family");

  const [isValid, setIsValid] = useState(false);

  
  // const token = useRecoilValue(userToken);
  // const [selectedKidId, setSelectedKidId] = useState<number | null>(null);
  // const [selectedKidName, setSelectedKidName] = useState<string | null>(null);

  // const [kidsData, setKidsData] = useState<KidData[]>([]);

  // useEffect(() => {
  //   axios
  //     .get(baseUrl + `api/relationship`, AxiosHeader({ token }))
  //     .then((response) => {
  //       if (response.data.data && response.data.data.relationList) {
  //         setKidsData(response.data.data.relationList);
  //       } else {
  //         console.error("Unexpected response:", response);
  //       }
  //     })
  //     .catch((error) => console.error("Error:", error));
  // }, [token]);

  // const handleKidClick = (id: number, name: string) => {
  //   if (selectedKidId === id) {
  //     setSelectedKidId(null);
  //     setSelectedKidName(null);
  //   } else {
  //     setSelectedKidId(id);
  //     setSelectedKidName(name);
  //   }
  // };

  const options: SendOptionS[] = [
    { value: "family", label: "가족" },
    { value: "account", label: "계좌" },
  ];

  const selectedStyle = (value: SendOption) => {
    if (sendOption === value) {
      return "bg-white";
    } else {
      return "bg-gray";
    }
  };

  const handleSelectOption = (value: SendOption) => {
    setSendOption(value);
  };

  return (
    <div className="mx-8">
      <div>
        <div className="text-l text-center">누구에게 보낼래요?</div>
        <div className="p-2 my-6 w-[50vw] mx-auto rounded-lg bg-gray flex">
          {options.map((option) => (
            <div
              className={`rounded-lg p-1 flex-1 text-center ${selectedStyle(
                option.value
              )}`}
              key={option.value}
              onClick={() => handleSelectOption(option.value)}
            >
              {option.label}
            </div>
          ))}
        </div>
      </div>
      {sendOption === "family" && <div></div>}
      {sendOption === "account" && (
        <div>
          <AccountSelectForm setIsValid={setIsValid} />
          <RechargeAccountList />
        </div>
      )}
      <FullBtn isDone={isValid} buttonLink="/transfer/input" />
    </div>
  );
}

export default TransferSelectForm;
