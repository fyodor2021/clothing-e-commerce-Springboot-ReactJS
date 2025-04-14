import {createContext, useEffect, useState} from 'react'
import axios from 'axios'
const FilterContext = createContext();
function FilterProvider({children}){
    const [filter,setFilter] = useState();
    const [predictions,setPredictions] = useState();

    const searchPrediction = (term) => {
        axios.get("api/product/search/" + term)
        .then(res => setPredictions(res.data))
    }

    const valueProvided = { 
        searchPrediction,
        predictions,
        setFilter,
    }
    return <FilterContext.Provider value={valueProvided}>
        {children}
    </FilterContext.Provider>

}
export {FilterProvider};
export default FilterContext;
