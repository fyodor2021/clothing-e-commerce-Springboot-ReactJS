import { useContext } from "react";
import ProductContext from "../context/ProductContext";

export default function useProductContext(){
    return useContext(ProductContext);
}