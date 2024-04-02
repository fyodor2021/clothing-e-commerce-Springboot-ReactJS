import { createContext, useEffect, useState } from "react"
import axios from 'axios'
import useAuthContext from "../hooks/useAuthContext";
const OrderContext = createContext();
function OrderProvider({ children }) {
    const { loggedUser } = useAuthContext();
    const [orders, setOrders] = useState();
    useEffect(() => {
         getLoggedUserOrders();
    },[loggedUser])
    const placeOrder = async (orderRequest) => {
        await axios.post("/api/order", orderRequest)
            .catch(err => console.log(err))
    }

    const getLoggedUserOrders = async () => {
        if (loggedUser) {
            await axios.get('/api/order/' + loggedUser.email).then(res => {
                setOrders(res.data)
            })
        }
    }

    const valueProvided = {
        placeOrder,
        getLoggedUserOrders,
        orders
    }
    return <OrderContext.Provider value={valueProvided}>
        {children}
    </OrderContext.Provider>
}
export { OrderProvider }
export default OrderContext