import { createContext, useState } from "react";
const ValidationContext = createContext();
function ValidationProvider({ children }) {
    const [valMessage, setValMessage] = useState('');
    function validateEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(emailRegex.test(email)){
            return true;
        }else{
            setValMessage("invalid email")
            return false;
        }
    }
    function validateAddress(address) {
        return address.trim() !== '';
    }
    function validateName(name) {
        if(/^[a-zA-Z]+$/.test(name)){
            return true;
        }else{
            setValMessage("invalid name")
            return false;
        }
    }
    function validatePassword(password) {
        if(password.length >= 8){
            return true;
        }else{
            setValMessage("invalid password")
            return false;
        }
    }
    function validatePasswordRetype(password, passwordRetype) {
        if(password === passwordRetype){
            return true;
        }else{
            setValMessage("passwords don't match")
            return false;
        }
    }
    const valueProvided = { valMessage,
        setValMessage,
        validateEmail,
        validateAddress,
        validateName,
        validatePassword,
        validatePasswordRetype
    }
    return <ValidationContext.Provider value={valueProvided}>
        {children}
    </ValidationContext.Provider>
}
export { ValidationProvider };
export default ValidationContext;