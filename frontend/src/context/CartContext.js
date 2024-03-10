import { createContext, useState } from "react";
import axios from "axios";
import useGeneralContext from "../hooks/useGeneralContext";

const CartContext = createContext();
function CartProvider({ children }) {
    const {setCookie,getCookie} = useGeneralContext();
    const [cartProducts, setCartProducts] = useState([]);
    const createCart = () => {
            axios.post("http://localhost:3003/api/cart",{
                userId: 0,
                products: []
            }).then(res => setCookie('cart',res.data, 1)).catch(err => console.log(err))
    }
    const addToCart = async (product) => {
        const cartId = getCookie('cart')
        const cartRequest = {
            cartId,product
        }
        const res = await axios
        .post("http://localhost:3003/api/cart/add", cartRequest)
    }
    const getCartProducts = () => {
        const cartId = getCookie('cart')
        axios.get("http://localhost:3003/api/cart/products/" + cartId)
        .then(res => setCartProducts(res.data))
    }
    const valueProvided = {
        createCart,
        addToCart,
        getCartProducts,
        cartProducts
    }
    return <CartContext.Provider value={valueProvided}>
                {children}
        </CartContext.Provider>
}
export { CartProvider };
export default CartContext;