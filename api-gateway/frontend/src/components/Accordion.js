import { useState } from "react";
import { GoChevronDown, GoChevronUp } from "react-icons/go";
import arzBrand from "../statics/arz-brand.png";
import { useDeleteCardInfoMutation } from "../store";
import { HiCreditCard } from "react-icons/hi2";
import { RiMastercardLine } from "react-icons/ri";
export default function Accordion({ items }) {
  const [expandedIndex, setExpandedIndex] = useState(null);
  const [deleteCardInfo] = useDeleteCardInfoMutation();
  const handleClick = (index) => {
    setExpandedIndex((currentExpandedIndex) => {
      if (currentExpandedIndex === index) {
        return null;
      } else {
        return index;
      }
    });
  };
  const handleDeleteWallet = (cardId) => {
    deleteCardInfo(cardId);
  };
  let renderedItems;
  if (items) {
    renderedItems = items.map((item, index) => {
      console.log(item);
      const expanded = index === expandedIndex;
      const icon = (
        <span className="text-2xl">
          {expanded ? <GoChevronDown /> : <GoChevronUp />}
        </span>
      );
      return (
        <div key={index}>
          <div
            className="flex bg-gray-50 border-b  justify-around items-center cursor-pointer"
            onClick={() => handleClick(index)}
          >
            <div>
              {item.label}
              {icon}
            </div>
            <div>
              <div className="checkout-payment-method-wallet m-0">
                <HiCreditCard className="text-black text-4xl m-1" />
                <div>
                  {item.brand} ending with {item.lastFourDigit}
                </div>
              </div>
            </div>
          </div>
          {expanded && (
            <div className="border-b p-5  payment-method-container">
              <div className="flex justify-center p-10 bg-gray-200">
                <div className="w-64 h-40 bg-black rounded-lg shadow-lg">
                  <div className="flex justify-between m-2">
                    <HiCreditCard className="text-white text-3xl" />
                    <RiMastercardLine className="text-white text-3xl" />
                  </div>
                  <div className="flex justify-center mt-4">
                    <h1 className="text-gray-400 font-thin font-os text-sm flex flex-col justify-center items-center">
                      <div>{item.bankName}</div>
                      <div>XXXX XXXX XXXX {item.lastFourDigit}</div>
                    </h1>
                  </div>
                  <div className="flex flex-col justfiy-end mt-4 p-4 text-gray-400 font-quick">
                    <p className="font-bold text-xs">{item.date}</p>
                    <h4 className="uppercase tracking-wider font-semibold text-xs">
                      {item.firstname} {item.lastname}
                    </h4>
                  </div>
                </div>
              </div>
              <div className="w-full flex justify-end">
                <div className="flex-row">
                  <button
                    className="button p-2"
                    onClick={() => handleDeleteWallet(item.cardInfoId)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          )}
        </div>
      );
    });
  }

  return <div className="border-x border-t rounded">{renderedItems}</div>;
}
