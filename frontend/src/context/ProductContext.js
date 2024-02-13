import { createContext } from "react";
import axios from 'axios'
import { useState } from 'react'
const ProductContext = createContext();
function Provider({ children }) {
    const [products, setProducts] = useState();
    const [isLoading, setIsLoading] = useState(true);
    const [product, setProduct] = useState()
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
        const res = axios.get("http://localhost:5000/api/product/" + productId)
        .then((response) => {
            setProduct(response.data)
        })
        setIsLoading(false)
    }
    const valueProvided = {
        fetchProducts,
        products,
        isLoading,
        fetchProductDetails,
        product
    }
    return <ProductContext.Provider value={valueProvided}>
        {children}
    </ProductContext.Provider>
}

export { Provider };
export default ProductContext;