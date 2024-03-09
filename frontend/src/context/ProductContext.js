import { createContext } from "react";
import axios from 'axios'
import { useState } from 'react'
import useUserContext from "../hooks/useUserContext";
const ProductContext = createContext();
function ProductProvider({ children }) {
    const [products, setProducts] = useState();
    const [isLoading, setIsLoading] = useState(true);
    const [product, setProduct] = useState();
    const [reviews, setReviews] = useState([]);
    const {fetchUser} = useUserContext();
    const fetchProducts = () => {
        axios.get("http://localhost:5000/api/product")
            .then((response) => {
                setProducts(response.data)
                setIsLoading(false)
            }).catch((err) => {
                console.log(err)
            })
    }
    const fetchProductDetails = (productId) => {
        setIsLoading(true)
        console.log(productId)
        const res = axios.get("http://localhost:5000/api/product/image/" + productId)
        .then((response) => {
            setProduct(response.data)
        })
        setIsLoading(false)
    }
    const fetchProductReviews = (productId) => {
        setIsLoading(true)
        const res = axios.get("http://localhost:3001/api/review/product/" + productId)
        .then((response) => {
            setReviews(response.data)
        })
        console.log(res)
        setIsLoading(false)
    }
    const valueProvided = {
        fetchProducts,
        products,
        isLoading,
        fetchProductDetails,
        product,
        fetchProductReviews,
        reviews
    }
    return <ProductContext.Provider value={valueProvided}>
        {children}
    </ProductContext.Provider>
}

export { ProductProvider };
export default ProductContext;