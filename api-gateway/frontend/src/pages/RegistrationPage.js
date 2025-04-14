import { useState, useEffect, useRef } from "react";
import Input from "../components/Input";
import josedorCard from "../statics/josedor-card.png";
import { useNavigate, useSearchParams } from "react-router-dom";
import { useRegisterMutation } from "../store/index.js";
import { useDispatch, useSelector } from "react-redux";
import {
  setEmail,
  setFname,
  setLname,
  setPassword,
  setPasswordRetype,
  setAddress,
} from "../store/index.js";
import { setResMessage } from "../store/index.js";
export default function RegistrationPage() {
  const {
    email,
    fname,
    lname,
    address,
    password,
    passwordRetype,
    emailVal,
    fnameVal,
    lnameVal,
    addressVal,
    passwordVal,
    passwordRetypeVal,
    emailValMes,
    fnameValMes,
    lnameValMes,
    addressValMes,
    passwordValMes,
    passwordRetypeValMes,
  } = useSelector((state) => {
    return state.input;
  });
  const { resMessage } = useSelector((state) => {
    return state.validation;
  });
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [register, resResults] = useRegisterMutation();
  console.log(resResults);
  useEffect(() => {
    dispatch(setResMessage(""));
  }, []);
  useEffect(() => {
    if (resResults.isSuccess) {
      if (!resResults.error) {
        dispatch(setResMessage("User Registered Successfully"));
        navigate("/login");
      } else {
        if (resResults.error.status) {
          dispatch(setResMessage("User is already registed to our database"));
        }
      }
    }
  }, [resResults]);
  const handleClick = () => {
    // setReg(false);
  };
  const handleLoginNavigate = () => {
    navigate("/login");
  };
  const onFormSubmit = async (event) => {
    event.preventDefault();
    if (
      emailVal &&
      fnameVal &&
      lnameVal &&
      passwordVal &&
      passwordRetypeVal &&
      addressVal
    ) {
      const user = {
        email,
        firstname: fname,
        lastname: lname,
        password,
        passwordRetype,
        address,
      };
      register(user);
    } else {
      dispatch(
        setResMessage("please complete form with the required information")
      );
    }
  };
  const inputFieldStyle = "input-field h-[35px] text-[.95rem] w-full rounded-[3px]";
  const inputLabelStyle = "input-label text-[.85rem] m-0 p-1";
  return (
    <div className="flex flex-col-reverse w-full pt-48 xl:flex-row xl:pt-0">
      <div className="w-full flex justify-center items-center">
        <form
          onSubmit={onFormSubmit}
          className="bg-[#f5f4f4] w-full md:w-[50%] p-3 rounded-xl"
        >
                    <div className="p-3 h-36 flex items-start flex-col justify-center">
            <div className="text-2xl text-bold">Welcome!</div>
            <div  className="text-xl">Let's get you Registered!</div>
          </div>
          <div className="flex w-full">
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
            setState={setEmail}
            state={email}
            val={emailVal}
            valMes={emailValMes}
            htmlFor="email"
            fieldClassName={inputFieldStyle}
            labelClassName={inputLabelStyle}
            labelContent="Email: "
            required={true}
            type="text"
          />

          <Input
            setState={setAddress}
            state={address}
            val={addressVal}
            valMes={addressValMes}
            address={true}
          />

          <Input
            setState={setPassword}
            state={password}
            val={passwordVal}
            valMes={passwordValMes}
            htmlFor={"password"}
            labelContent={"Password: "}
            fieldClassName={inputFieldStyle}
            labelClassName={inputLabelStyle}
            required={true}
            type="password"
          />

          <Input
            setState={setPasswordRetype}
            state={passwordRetype}
            val={passwordRetypeVal}
            valMes={passwordRetypeValMes}
            htmlFor={"passwordretype"}
            labelContent={"Retype Password: "}
            fieldClassName={inputFieldStyle}
            labelClassName={inputLabelStyle}
            required={true}
            type="password"
          />
          <span style={{ color: "red", padding: "3px 10px" }}>
            {resMessage}
          </span>
          <span style={{ color: "red", padding: "3px 10px" }}>{}</span>
          <div className="login-button-container">
            <button className="button" type="submit">
              Register
            </button>
            <div style={{ margin: "10px" }}>
              <span>Or </span>
              <span
                style={{ color: "red", cursor: "pointer" }}
                onClick={handleLoginNavigate}
              >
                Login
              </span>
            </div>
          </div>
        </form>
      </div>
      <div className="w-full flex justify-center items-center xl:h-screen xl:bg-black xl:w-[80%] mb-2 xl:mb-0 ">
        <img src={josedorCard} className="md:w-[50%] xl:w-full rounded-xl " />
      </div>
    </div>
  );
}
