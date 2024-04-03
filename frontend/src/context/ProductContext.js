import { createContext, useEffect, useMemo } from "react";
import axios from 'axios'
import { useState } from 'react'
import useAuthContext from "../hooks/useAuthContext";
const ProductContext = createContext();
function ProductProvider({ children }) {
    const [products, setProducts] = useState();
    const [isLoading, setIsLoading] = useState(true);
    const [product, setProduct] = useState();
    const [reviews, setReviews] = useState([]);
    const [orderProductImages, setOrderProductImages] = useState();
    const {fetchUser} = useAuthContext();

    const fetchProducts = () => {
        axios.get('/api/product',{
            withCredentials:true
        })
            .then((response) => {
                setProducts(response.data)
                setIsLoading(false)
            }).catch((err) => {
                console.log(err)
            })
    }
    useMemo(() => {
        fetchProducts()
    },[products])
    const fetchProductDetails = (productId) => {
        setIsLoading(true)
        const res = axios.get("api/product/image/" + productId)
        .then((response) => {
            setProduct(response.data)
        })
        setIsLoading(false)
    }
    const fetchProductReviews = (productId) => {
        setIsLoading(true)
        const res = axios.get("api/review/product/" + productId)

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
        setProducts,
        reviews,
    }
    return <ProductContext.Provider value={valueProvided}>
        {children}
    </ProductContext.Provider>
}

export { ProductProvider };
export default ProductContext;