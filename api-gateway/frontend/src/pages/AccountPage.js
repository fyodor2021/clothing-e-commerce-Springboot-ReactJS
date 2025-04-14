import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import Wallet from "../components/Wallet";
import PersonalInfo from "../components/PersonalInfo";
import josedorCard from "../statics/josedor-card.png";
import { MdOutlineWavingHand } from "react-icons/md";
export default function AccountPage() {
  const [personalSelected, setPersonalSelected] = useState(true);
  const [selected, setSelected] = useState(true);
  const { state } = useLocation();
  useEffect(() => {
    if (state == "add payment") {
      setSelected("My Wallet");
    }
  }, []);
  return (
    <div className="mt-44 md:mt-36 xl:mt-48 py-10 w-full h-screen h-[calc(100vh-176px)] md:h-[calc(100vh-144px)] xl:h-[calc(100vh-192px)] bg-black bg-opacity-80 font-semibold flex justify-center items-center">
      <div className="xl:w-[75%] flex flex-col xl:flex-row h-full transform translate-x-0 transition ease-in-out duration-700">
        <div className="w-full xl:w-[50%] xl:px-14 xl:py-20 px-1 py-2 md:h-[calc(100vh-144px)] xl:h-[calc(100vh-300px)] ">
          <PersonalInfo />
        </div>
        <div className="flex xl:w-[50%] bg-white flex-col xl:px-14 xl:py-20 px-1 py-2 justify-between ">
          {state === "add payment" ? (
            <Wallet checkoutAddPayment={true} />
          ) : (
            <Wallet />
          )}
        </div>
      </div>
    </div>
  );
}

<div className="account-page page-padding-top"></div>;
