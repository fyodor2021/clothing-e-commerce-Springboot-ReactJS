import { useContext } from "react";
import CartContext from "../context/CartContext.js";

export default function useCartContext(){
    return useContext(CartContext);
}