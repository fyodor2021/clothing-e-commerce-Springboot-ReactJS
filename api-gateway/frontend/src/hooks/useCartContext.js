import CartContext from "../context/cartContext";
import {useContext} from 'react';


export default function useCartContext(){
    return useContext(CartContext);
}