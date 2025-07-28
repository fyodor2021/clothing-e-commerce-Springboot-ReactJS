import { createContext, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setCartItemCounter, setCartItems, store } from '../store';
const CartContext = createContext();
function CartProvider({ children }) {

  const dispatch = useDispatch();

  const { cartItemCounter } = useSelector((state) => state.cartSlice);
  const handleAddToCart = (productId, size, quantity = 1) => {
    const addedProduct = { productId, quantity, size };
    const cart = window.localStorage.getItem('cart');
    let storedCart = cart ? JSON.parse(cart) : [];
    let found = false;
    for (let [index, cartItem] of storedCart.entries()) {
      if (
        addedProduct.productId === cartItem.productId &&
        addedProduct.size === cartItem.size
      ) {
        found = true;
        cartItem.quantity = cartItem.quantity + addedProduct.quantity;
        if(cartItem.quantity <= 0){
         storedCart.splice(index, 1)
        }
      }
    }
    if (!found) {
      storedCart.push(addedProduct);
    }
    window.localStorage.setItem('cart', JSON.stringify(storedCart));
    dispatch(setCartItems(storedCart));
    dispatch(setCartItemCounter(cartItemCounter + quantity));
  };

  const getStorageCartItems = () => {
    const cart = window.localStorage.getItem('cart');
    const storedCart = cart ? JSON.parse(cart) : [];
    console.log("storedCart", storedCart)
    let cartItemsCount = 0;
    for (let cartItem of storedCart) {
      cartItemsCount += cartItem.quantity;
    }
    dispatch(setCartItemCounter(cartItemsCount));
    dispatch(setCartItems(storedCart));
  };
  const valueProvided = {
    handleAddToCart,
    getStorageCartItems,
  };
  return (
    <CartContext.Provider value={valueProvided}>
      {children}
    </CartContext.Provider>
  );
}
export { CartProvider };
export default CartContext;
