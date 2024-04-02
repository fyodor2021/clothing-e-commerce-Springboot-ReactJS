import OrderContext from "../context/OrderContext";
import { useContext } from "react";
export default function useOrderContext(){
    return useContext(OrderContext)
}