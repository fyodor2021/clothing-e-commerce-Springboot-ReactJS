import { createContext, useEffect, useMemo } from "react";
import axios from 'axios'
import { useState } from 'react'
import { useFetchProductsQuery } from "../store/apis/productApi";
const ProductContext = createContext();
function ProductProvider({ children }) {
    const [products, setProducts] = useState();
    const [isLoading, setIsLoading] = useState(true);
    const [product,setProduct] = useState()
    const [womenProduct, setWomentProduct] = useState([]);
    const [menProduct, setMenProduct] = useState([]);
    const [kidsProduct, setKidsProduct] = useState([]);
    const [reviews, setReviews] = useState([]);
    const {data, error, loading} = useFetchProductsQuery();

    useEffect(() => {
        if(data){
            data.map(product => {
                if(product.gender == 'women'){
                    setWomentProduct(prev  => [...prev, product])
                }else if(product.gender == 'men'){
                    setMenProduct(prev  => [...prev, product])
                }else{
                    setKidsProduct(prev  => [...prev, product])
                }
            })
        }
    }, [data])

const valueProvided = {
    products,
    isLoading,
    setIsLoading,
    product,
    setProducts,
    reviews,
    menProduct,
    womenProduct,
    kidsProduct
}
return <ProductContext.Provider value={valueProvided}>
    {children}
</ProductContext.Provider>
}

export { ProductProvider };
export default ProductContext;