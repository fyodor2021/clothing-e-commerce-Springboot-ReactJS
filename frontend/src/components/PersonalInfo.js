import { useState, useRef, useEffect, useCallback } from "react"
import Input from '../components/Input'
import useAuthContext from '../hooks/useAuthContext'
import useValidationContext from "../hooks/useValidationContext";
import { useNavigate } from "react-router-dom";
export default function PersonalInfo() {
    const {setUserChanged,userChanged,loggedUser} = useAuthContext();
    const [userEdit, setUserEdit] = useState(false);
    const [addressEdit, setAddressEdit] = useState(false);
    const [passwordEdit, setPasswordEdit] = useState(false);
    const [firstname, setFirstname] = useState(loggedUser.firstname);
    const [lastname, setLastname] = useState(loggedUser.lastname);
    const [address, setAddress] = useState(loggedUser.address);
    const [passwordRetype, setPasswordRetype] = useState('*************');
    const [password, setPassword] = useState('*************');
    const navigate = useNavigate();
    const autoCompleteRef = useRef();
    const addressRef = useRef();
    const options = {
        componentRestrictions: { country: 'CA' },
        fields: ["formatted_address", "name"],
        types: ["address"]
    };
    useEffect(() => {
        autoCompleteRef.current = new window.google.maps.places.Autocomplete(
            addressRef.current,
            options
        );
        autoCompleteRef.current.addListener("place_changed", placeChangedListener);
        return window.removeEventListener("place_changed", placeChangedListener)
    }, [address]);
    const {
        validateName,
        validatePassword,
        validatePasswordRetype } = useValidationContext();
    useEffect(() => {
       setUserChanged(false) 
    },[])
    const { updateUser } = useAuthContext();
    const editToggle = (editable) => {
        switch (editable) {
            case "user":
                setUserEdit(true)
                setAddressEdit(false)
                setPasswordEdit(false)
                break;
            case "address":
                setAddressEdit(true)
                setUserEdit(false)
                setPasswordEdit(false)
                break;
            case "password":
                setPasswordEdit(true)
                setUserEdit(false)
                setAddressEdit(false)
                break;
        }
    }
    const cancelEditAction = (editable) => {
        switch (editable) {
            case "user":
                setUserEdit(false)
                break;
            case "address":
                setAddressEdit(false)
                break;
            case "password":
                setPasswordEdit(false)
                break;
        }
    }
    const submitAction = async (event, editable) => {
        event.preventDefault();
        let user;
        switch (editable) {
            case "user":
                if (firstname == loggedUser.firstname && lastname == loggedUser.lastname) {
                    setUserEdit(false)
                    break;
                }
                if (validateName(firstname) && validateName(lastname)) {
                    user = {
                        firstname,
                        lastname,
                        email:loggedUser.email,
                        updateForm: 'user'
                    }

                    try {
                        await updateUser(user);
                        setUserChanged(!userChanged);
                        setUserEdit(false);
                        // Navigate or perform other actions if needed
                    } catch (error) {
                        console.error("Error updating user:", error);
                        // Handle error (e.g., display error message)
                    }
                }
                break;
            case "address":
                if (address === loggedUser.address) {
                    setAddressEdit(false)
                    break;
                }
                user = {
                    address,
                    email:loggedUser.email,
                    updateForm: 'address'
                }
                try {
                    await updateUser(user);
                    setUserChanged(!userChanged);
                    setAddressEdit(false);
                    // Navigate or perform other actions if needed
                } catch (error) {
                    console.error("Error updating user:", error);
                    // Handle error (e.g., display error message)
                }
                break;
            case "password":
                if (password === '*************') {
                    setPasswordEdit(false)
                    break;
                }
                if (validatePassword(password) && validatePasswordRetype(password, passwordRetype)) {
                    user = {
                        password,
                        email:loggedUser.email,
                        updateForm: 'password'
                    }
                    try {
                        await updateUser(user);
                        setUserChanged(!userChanged);
                        setPasswordEdit(false);
                        // Navigate or perform other actions if needed
                    } catch (error) {
                        console.error("Error updating user:", error);
                        // Handle error (e.g., display error message)
                    }
                }
                break;
        }
    }

    const placeChangedListener = () => {
        const place = autoCompleteRef.current.getPlace();
        if (place && place.formatted_address) {
            setAddress(place.formatted_address);
        }
    }
    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            event.preventDefault()
            setAddress(event.target.value)
        }
    }
    const handleAddressChange = (event) => {
        setAddress(event.target.value)
    }
    return <div>
        <h1 className="account-page-headers">Your Personal Information</h1>
        {!userEdit ?
            <div className="account-info-item">
                <div>
                    <div className="account-info-label">Full name</div>
                    <div>{loggedUser.firstname} {loggedUser.lastname}</div>
                </div>
                <div>
                    <button onClick={() => editToggle('user')}>Edit</button>
                </div>
            </div>
            :
            <div className="account-info-item-change">
                <form onSubmit={(event) => submitAction(event, 'user')}>
                    <div>
                        <Input
                            state={[firstname, setFirstname]}
                            htmlFor={'firstname'}
                            labelContent={'Firstname: '}
                            fieldClassName='info-edit-form-fields'
                            labelClassName='account-info-label'
                            required={true}
                            type='text'

                        />
                        <Input
                            state={[lastname, setLastname]}
                            htmlFor={'lastname'}
                            labelContent={'Lastname: '}
                            fieldClassName='info-edit-form-fields'
                            labelClassName='account-info-label'
                            required={true}
                            type='text'
                        />

                    </div>
                    <div>
                        <button className="button info-edit-form-buttons" type="submit">Submit</button>
                        <button className="button cancel-button info-edit-form-buttons" onClick={() => cancelEditAction('user')}>Cancel</button>
                    </div>
                </form>
            </div>
        }
        {<div className="account-info-item">
            <div>
                <div className="account-info-label">Email</div>
                <div className="account-info-label">{loggedUser.email}</div>
            </div>
            <div>
                <div className="account-info-label">Edit</div>
            </div>
        </div>
        }
        {!addressEdit ? <div className="account-info-item">
            <div>
                <div className="account-info-label">Address</div>
                <div>{loggedUser.address}</div>
            </div>
            <div>
                <button onClick={() => editToggle('address')}>Edit</button>
            </div>
        </div>
            :
            <div className="account-info-item-change">
                <form onSubmit={(event) => submitAction(event, 'address')}>

                    <div className='reg-input'>
                        <div className="input-component-container">
                            <label className="input-component-label input-label" htmlFor="addressChange">Adress: <span style={{ color: 'red' }}>*</span></label>
                            <input id="addressChange"
                                onKeyDown={handleKeyDown}
                                className="info-edit-form-fields" type='text'
                                htmlFor="addressChange"
                                onChange={handleAddressChange}
                                ref={addressRef}
                                value={address}
                            />
                        </div>
                    </div>
                    <div>
                        <button className="button info-edit-form-buttons">Submit</button>
                        <button className="button cancel-button info-edit-form-buttons" onClick={() => cancelEditAction('address')}>Cancel</button>
                    </div>
                </form>

            </div>
        }
        {!passwordEdit ? <div className="account-info-item">
            <div>
                <div className="account-info-label">Password</div>
                <div>*************</div>
            </div>
            <div>
                <button onClick={() => editToggle('password')}>Edit</button>
            </div>
        </div> :
            <div className="account-info-item-change">
                <form onSubmit={(event) => submitAction(event, 'password')}>
                    <div>
                        <Input
                            state={[password, setPassword]}
                            htmlFor={'password'}
                            labelContent={'New Password: '}
                            fieldClassName='info-edit-form-fields'
                            labelClassName='account-info-label'
                            required={true}
                            type='password'
                        />
                        <Input
                            state={[passwordRetype, setPasswordRetype]}
                            htmlFor={'passwordRetype'}
                            labelContent={'Password Retype: '}
                            fieldClassName='info-edit-form-fields'
                            labelClassName='account-info-label'
                            required={true}
                            type='password'
                            Autocomplete="off"
                        />
                    </div>
                    <div>
                        <button className="button info-edit-form-buttons">Submit</button>
                        <button className="button cancel-button info-edit-form-buttons" onClick={() => cancelEditAction('password')}>Cancel</button>
                    </div>
                </form>

            </div>
        }
    </div>
}