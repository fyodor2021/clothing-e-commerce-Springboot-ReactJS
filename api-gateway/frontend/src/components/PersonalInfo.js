import { useState, useRef, useEffect } from "react";
import Input from "../components/Input";
import { useLoggedUserQuery, useUpdateUserMutation } from "../store";
import { useDispatch, useSelector } from "react-redux";
import {
  setFname,
  setLname,
  setAddress,
  setPassword,
  setPasswordRetype,
} from "../store";
export default function PersonalInfo() {
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
  const loggedUser = useSelector((state) => {
    return state.user;
  });
  const [userEdit, setUserEdit] = useState(false);
  const [addressEdit, setAddressEdit] = useState(false);
  const [passwordEdit, setPasswordEdit] = useState(false);
  const [updateUser] = useUpdateUserMutation();
  const dispatch = useDispatch();
  const { value: token } = useSelector((state) => {
    return state.token;
  });

  const editToggle = (editable) => {
    switch (editable) {
      case "user":
        setUserEdit(true);
        setAddressEdit(false);
        setPasswordEdit(false);
        break;
      case "address":
        setAddressEdit(true);
        setUserEdit(false);
        setPasswordEdit(false);
        break;
      case "password":
        setPasswordEdit(true);
        setUserEdit(false);
        setAddressEdit(false);
        break;
    }
  };
  const cancelEditAction = (editable) => {
    switch (editable) {
      case "user":
        setUserEdit(false);
        break;
      case "address":
        setAddressEdit(false);
        break;
      case "password":
        setPasswordEdit(false);
        break;
    }
  };
  const submitAction = async (event, editable) => {
    event.preventDefault();
    let user;
    switch (editable) {
      case "user":
        if (fname == loggedUser.fname && lname == loggedUser.lname) {
          setUserEdit(false);
          break;
        } else if (fnameVal && lnameVal) {
          user = {
            firstname: fname,
            lastname: lname,
            email: loggedUser.email,
            updateForm: "user",
          };
          updateUser(user);
          setUserEdit(false);
        }
        break;
      case "address":
        if (address === loggedUser.address) {
          setAddressEdit(false);
          break;
        } else if (addressVal) {
          user = {
            address,
            email: loggedUser.email,
            updateForm: "address",
          };
          updateUser(user);
          setAddressEdit(false);
          break;
        }
      case "password":
        if (password === "*************") {
          setPasswordEdit(false);
          break;
        } else if (passwordVal && passwordRetypeVal) {
          user = {
            password,
            email: loggedUser.email,
            updateForm: "password",
          };
          updateUser(user);
          setPasswordEdit(false);
          break;
        }
    }
  };
  const accountInfoItem = "text-white flex justify-between m-2 border p-2";
  const accountInfoChange = "";
  const inputFieldStyle = "h-[35px] text-[.95rem] w-full ";
  const inputLabelStyle = "text-[.85rem] m-0 p-1 text-white";
  const button = 'button p-[.5rem]'
  return (
    loggedUser && (
      <div className="w-full">
        {!userEdit ? (
          <div>
            <h1 className="text-gray-300 text-4xl">
              Your Personal Information
            </h1>
            <div className={accountInfoItem}>
              <div>
                <div className="account-info-header">Full name</div>
                <div>
                  {loggedUser.fname} {loggedUser.lname}
                </div>
              </div>
              <div>
                <button onClick={() => editToggle("user")}>Edit</button>
              </div>
            </div>
          </div>
        ) : (
          <form
            className={accountInfoChange}
            onSubmit={(event) => submitAction(event, "user")}
          >
            <div className="flex flex-col sm:flex-row">
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
            <div>
              <button className={button} type="submit">
                Submit
              </button>
              <button
                className={button}
                onClick={() => cancelEditAction("user")}
              >
                Cancel
              </button>
            </div>
          </form>
        )}
        <div className={accountInfoItem}>
          <div>
            <div className="account-info-header">Email</div>
            <div className="account-info-label">{loggedUser.email}</div>
          </div>
          <div>
            <div style={{ color: "gray", fontSize: "15px" }}>Edit</div>
          </div>
        </div>

        {!addressEdit ? (
          <div className={accountInfoItem}>
            <div>
              <div className="account-info-header">Address</div>
              <div>{loggedUser.address}</div>
            </div>
            <div>
              <button onClick={() => editToggle("address")}>Edit</button>
            </div>
          </div>
        ) : (
          <form
            className={accountInfoChange}
            onSubmit={(event) => submitAction(event, "address")}
          >
            <div className="reg-input">
              <Input
                setState={setAddress}
                state={address}
                val={addressVal}
                valMes={addressValMes}
                address={true}
              />
            </div>
            <div>
              <button className={button}>Submit</button>
              <button
                className={button}
                onClick={() => cancelEditAction("address")}
              >
                Cancel
              </button>
            </div>
          </form>
        )}

        {!passwordEdit ? (
          <div className={accountInfoItem}>
            <div>
              <div className="account-info-header">Password</div>
              <div>*************</div>
            </div>
            <div>
              <button onClick={() => editToggle("password")}>Edit</button>
            </div>
          </div>
        ) : (
          <form
            className={accountInfoChange}
            onSubmit={(event) => submitAction(event, "password")}
          >
            <div className="flex-row">
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
            </div>
            <div>
              <button className={button}>Submit</button>
              <button
                className={button}
                onClick={() => cancelEditAction("password")}
              >
                Cancel
              </button>
            </div>
          </form>
        )}
      </div>
    )
  );
}
