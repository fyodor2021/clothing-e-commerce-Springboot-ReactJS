import { useContext } from "react";
import validationContext from "../context/ValidationContext.js";

export default function useValidationContext(){
    return useContext(validationContext);
}