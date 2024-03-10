import { createContext } from "react";
const GeneralContext = createContext();
function GeneralProvider({children}){
    const setCookie = (name,value,expiration) => {
        let date = new Date();
        date.setTime(date.getTime() + (expiration * 24 * 60 * 60 * 1000))
        const expires = "expires=" + date.toUTCString();
        document.cookie = name + "=" + value + "; " + expires + "; path=/";
    }
    const getCookie = (name) => {
        const cookies = 
        document.cookie.split(';')
        .map(cookie => cookie.split('='))
        .reduce((acc, [key,value]) => (
            {...acc, [key.trim()]: decodeURIComponent(value)}
        ),{})
        return cookies[name]
    }
    const valueProvided = {setCookie,getCookie}
    return <GeneralContext.Provider value={valueProvided}>
        {children}
    </GeneralContext.Provider>
}
export {GeneralProvider};
export default GeneralContext;