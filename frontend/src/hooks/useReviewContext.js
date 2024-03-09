import { useContext } from "react";
import ReviewContext from "../context/ReviewContext";

export default function useReivewContext(){
    return useContext(ReviewContext);
}