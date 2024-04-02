import { useContext } from "react";
import PaymentContext from "../context/PaymentContext";

export default function usePaymentContext(){
    return useContext(PaymentContext);
}