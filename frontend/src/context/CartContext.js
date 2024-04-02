import { createContext, useEffect, useState } from "react";
import axios from "axios";
import useValidationContext from "../hooks/useValidationContext";
import { useCookies } from "react-cookie";
const CartContext = createContext();
function CartProvider({ children }) {
    const [cartProducts, setCartProducts] = useState([]);
    const [cookies] = useCookies()
    useEffect(() => {
        getCartProducts();

    },[])
    const addToCart = (product) => {
        axios.post("api/cart/add", {product}).then(res => getCartProducts())
    }
    const getCartProducts = () => {
        axios.get(`api/cart/products`,{withCredentials: true})
        .then(res => {if(res){setCartProducts(res.data)}})
    }
    const productCountDecrement = (incDecRequest) => {
        axios.post("api/cart/product/dec",incDecRequest).then(res => getCartProducts())
    }
    const productCountIncrement = (incDecRequest) => {
        axios.post("api/cart/product/inc",incDecRequest).then(res => getCartProducts())
    }

    const emptyCart = async() => {
        await axios.put('api/cart/all')
    }
    const valueProvided = {
        addToCart,
        getCartProducts,
        cartProducts,
        productCountDecrement,
        productCountIncrement,
        emptyCart
    }   

    
    return <CartContext.Provider value={valueProvided}>
                {children}
        </CartContext.Provider>
}
export { CartProvider };
export default CartContext;