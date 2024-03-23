import { createContext, useState } from "react";
import axios from "axios";
import useGeneralContext from "../hooks/useGeneralContext";
import { useCookies } from "react-cookie";
const CartContext = createContext();
function CartProvider({ children }) {
    const {setCookie,getCookie} = useGeneralContext();
    const [cartProducts, setCartProducts] = useState([]);
    const [cookies] = useCookies()
//     const createCart = async () => {
//         axios.post("http://localhost:3003/api/cart/add", {
//     userId: null,
//     products: []
// }, {

//     withCredentials: true
// }).then(res => console.log(res.data));
//     }
//     }
//     const testCart = () => {
//         // axios.post("http://localhost:3003/api/cart/add",{
//         // Headers:{
//         //     'Content-Type':'application/json'
//         // },
//         // withCredentials:'included'

//         // })
//         // .then(res => console.log(res.data))
//         // axios.get("http://localhost:3003/api/cart/add",{
//         //     // userId: 0,
//         //     // products: []
//         //     withCredentials: true
//         // }).then(res => console.log(res.data))
//         axios.post("http://localhost:3003/api/cart/add", {
//     // userId: 0,
//     // products: []
// }, {

//     withCredentials: true
// }).then(res => console.log(res.data));
//     }
    const addToCart = (product) => {
        axios.post("api/cart/add", {product}).then(res => console.log(res))
    }
    const getCartProducts = () => {
        axios.get(`api/cart/products`,{withCredentials: true})
        .then(res => setCartProducts(res.data))
    }
    const valueProvided = {
        addToCart,
        getCartProducts,
        cartProducts,
    }
    return <CartContext.Provider value={valueProvided}>
                {children}
        </CartContext.Provider>
}
export { CartProvider };
export default CartContext;