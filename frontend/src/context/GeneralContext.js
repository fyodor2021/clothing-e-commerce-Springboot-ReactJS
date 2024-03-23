import { createContext, useState } from "react";
const GeneralContext = createContext();
function GeneralProvider({ children }) {
    const [valMessage, setValMessage] = useState('');
   
    const valueProvided = { valMessage,setValMessage }
    return <GeneralContext.Provider value={valueProvided}>
        {children}
    </GeneralContext.Provider>
}
export { GeneralProvider };
export default GeneralContext;