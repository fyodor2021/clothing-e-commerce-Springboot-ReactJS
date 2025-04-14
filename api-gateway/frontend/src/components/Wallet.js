import josedorBranding from "../statics/josedor-branding.png";
import { FiPlusCircle } from "react-icons/fi";
import { useEffect, useReducer, useRef, useState } from "react";
import Input from "./Input";
import Accordion from "./Accordion";
import {
  useFetchCardsInfoQuery,
  useAddPaymentMethodMutation,
  setFname,
  setLname,
  setSecurityCode,
  setCardNumber,
  setExpire,
} from "../store/index";
import { HiCreditCard } from "react-icons/hi2";
import { RiMastercardLine } from "react-icons/ri";
import { useDispatch, useSelector } from "react-redux";
import { BiFontSize } from "react-icons/bi";
export default function Wallet({ checkoutAddPayment }) {
  const loggedUser = useSelector((state) => {
    return state.user;
  });
  const [addPayment, setAddPayment] = useState(false);
  const dispatch = useDispatch();
  const { data } = useFetchCardsInfoQuery(loggedUser.email);
  const [addPaymentMethod] = useAddPaymentMethodMutation();
  const {
    fname,
    fnameVal,
    fnameValMes,
    lname,
    lnameVal,
    lnameValMes,
    cardNumber,
    cardNumberVal,
    cardNumberValMes,
    securityCode,
    securityCodeVal,
    securityCodeValMes,
    expire,
    expireVal,
    expireValMes,
  } = useSelector((state) => {
    return state.input;
  });
  console.log(data);
  useEffect(() => {
    if (checkoutAddPayment) {
      setAddPayment(true);
    }
  }, []);
  const handleAddPaymentMethod = () => {
    setAddPayment(true);
  };
  const cancelAddAction = () => {
    setAddPayment(false);
  };

  const handleAddPaymentSubmit = (event) => {
    event.preventDefault();
    const addPaymentRequest = {
      cardHolderFirstName: fname,
      cardHolderLastName: lname,
      cardNumber,
      cvv: securityCode,
      expiryDate: expire,
      userEmail: loggedUser.email,
    };
    addPaymentMethod(addPaymentRequest);
    dispatch(setFname(""));
    dispatch(setLname(""));
    dispatch(setSecurityCode(""));
    dispatch(setCardNumber(""));
    dispatch(setExpire(""));
    setAddPayment(false);
  };
  const inputFieldStyle = "input-field h-[35px] text-[.95rem] w-full ";
  const inputLabelStyle = "input-label text-[.85rem] m-0 p-1 text-black";
  const button = "button p-[.5rem]";

  return loggedUser ? (
    !addPayment ? (
      <div >
        <div className="flex align-center justify-between">
          <h1 className="account-page-headers">Your Payment Methods</h1>
          <div onClick={handleAddPaymentMethod}>
            <FiPlusCircle />
          </div>
        </div>
        <div className="flex justify-center p-10 bg-gray-200">
          <div className="w-64 h-40 bg-black rounded-lg shadow-lg">
            <div className="flex justify-between m-2">
              <HiCreditCard className="text-white text-3xl" />
              <RiMastercardLine className="text-white text-3xl" />
            </div>
            <div className="flex justify-center mt-4">
              <h1 className="text-gray-400 font-thin font-os">
                XXXX XXXX XXXX {}
              </h1>
            </div>
            <div className="flex flex-col justfiy-end mt-4 p-4 text-gray-400 font-quick">
              <p className="font-bold text-xs">12 / 17</p>
              <h4 className="uppercase tracking-wider font-semibold text-xs">
                Our customer
              </h4>
            </div>
          </div>
        </div>
        <Accordion items={data} />
      </div>
    ) : (
      <div >
        <div className="flex-row ">
          <h1 className="account-page-headers">Add Payment Method</h1>
        </div>
        <div className="">
          <form onSubmit={handleAddPaymentSubmit}>
            <div className="flex-row">
                <Input
                  setState={setFname}
                  state={fname}
                  val={fnameVal}
                  valMes={fnameValMes}
                  htmlFor="fname"
                  fieldClassName={inputFieldStyle}
                  labelClassName={inputLabelStyle}
                  labelContent="First Name: "
                  required={true}
                  type="text"
                />
                <Input
                  setState={setLname}
                  state={lname}
                  val={lnameVal}
                  valMes={lnameValMes}
                  htmlFor="lname"
                  fieldClassName={inputFieldStyle}
                  labelClassName={inputLabelStyle}
                  labelContent="Last Name: "
                  required={true}
                  type="text"
                />
            </div>
              <Input
                setState={setCardNumber}
                state={cardNumber}
                val={cardNumberVal}
                valMes={cardNumberValMes}
                htmlFor="card-number"
                fieldClassName={inputFieldStyle}
                labelClassName={inputLabelStyle}
                labelContent="Card Number: "
                required={true}
                type="text"
              />
            <div className="flex-row">
                <Input
                  setState={setSecurityCode}
                  state={securityCode}
                  val={securityCodeVal}
                  valMes={securityCodeValMes}
                  htmlFor="security-code"
                  fieldClassName={inputFieldStyle}
                  labelClassName={inputLabelStyle}
                  labelContent="Security Code: "
                  required={true}
                  type="text"
                />
                <Input
                  setState={setExpire}
                  state={expire}
                  val={expireVal}
                  valMes={expireValMes}
                  htmlFor="expire"
                  fieldClassName={inputFieldStyle}
                  labelClassName={inputLabelStyle}
                  labelContent="Expiration Date: "
                  required={true}
                  type="text"
                />
            </div>
            <div>
              <button className={button}>Submit</button>
              <button
                className={button}
                onClick={cancelAddAction}
              >
                Cancel
              </button>
            </div>
          </form>
        </div>
      </div>
    )
  ) : (
    ""
  );
}
