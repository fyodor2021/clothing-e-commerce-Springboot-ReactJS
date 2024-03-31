import { createContext, useState } from "react";
import axios from "axios";
import useValidationContext from "../hooks/useValidationContext";
import { useCookies } from "react-cookie";
const CartContext = createContext();
function CartProvider({ children }) {
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
    const valueProvided = {
        addToCart,
        getCartProducts,
        cartProducts,
        productCountDecrement,
        productCountIncrement
    }   

    
    return <CartContext.Provider value={valueProvided}>
                {children}
        </CartContext.Provider>
}
export { CartProvider };
export default CartContext;