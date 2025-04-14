import {createContext, useEffect, useState} from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { setCartItemCounter, setCartItems } from '../store';
const CartContext = createContext();
function CartProvider({children}){
    const dispatch = useDispatch();
    const {cartItemCounter} = useSelector(state => state.cartSlice)
    const handleAddToCart = (productId, size, quantity = 1) => {
        const addedProduct = { productId, quantity, size };
        const cart = window.localStorage.getItem("cart");
        let storedCart = cart ? JSON.parse(cart) : [];
        let found = false;
        for (let cartItem of storedCart) {
            if (addedProduct.productId === cartItem.productId && addedProduct.size === cartItem.size) {
                found = true;
                cartItem.quantity = cartItem.quantity + addedProduct.quantity;
            }
        }
        if (!found) {
            storedCart.push(addedProduct);
        }
        window.localStorage.setItem("cart", JSON.stringify(storedCart));
        dispatch(setCartItemCounter(cartItemCounter + 1));
    }
    const valueProvided = { 
        handleAddToCart
    }
    return <CartContext.Provider value={valueProvided}>
        {children}
    </CartContext.Provider>

}
export {CartProvider};
export default CartContext;
