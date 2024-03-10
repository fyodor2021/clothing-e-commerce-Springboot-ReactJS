import { useContext } from "react";
import GeneralContext from "../context/GeneralContext.js";

export default function useGeneralContext(){
    return useContext(GeneralContext);
}