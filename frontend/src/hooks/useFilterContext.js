import FilterContext from "../context/FilterContext";
import {useContext} from 'react';


export default function useFilterContext(){
    return useContext(FilterContext);
}