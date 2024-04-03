import { createContext, useEffect, useState } from "react";
import axios from 'axios'

const PaymentContext = createContext();
function PaymentProvider({ children }) {
    const [userCardsInfo, setUserCardsInfo] = useState();
    useEffect(() => {
        if (window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)) {
            getCardsInfo();
        }
    },[])
    const addPaymentMethod = async (addPaymentRequest) => {
        await axios.post('/api/wallet/add', addPaymentRequest)
            .then(res => console.log(res.data))
    }
    const getCardsInfo = async () => {
            await axios.get('/api/wallet/cards')
                .then(res => setUserCardsInfo(res.data))
    }
    const valueProvided = {
        addPaymentMethod,
        getCardsInfo,
        userCardsInfo
    }
    return <PaymentContext.Provider value={valueProvided}>
        {children}
    </PaymentContext.Provider>
}

export { PaymentProvider }
export default PaymentContext;