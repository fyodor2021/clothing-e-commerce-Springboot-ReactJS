import {createContext, useState} from 'react'
import axios from 'axios'
import useProductContext from '../hooks/useProductContext';
const FilterContext = createContext();
function FilterProvider({children}){
    const [predictions, setPredictions] = useState();
    const {setProducts} = useProductContext();
    const [filter,setFilter] = useState();
    const searchPrediction = (term) => {
        axios.get("api/product/search/" + term)
        .then(res => setPredictions(res.data))
    }
    const filterProductsBySearchTerm = async (searchTerm) => {
        await axios.get('/api/product/search/submit/' + searchTerm)
        .then(res => setProducts(res.data));
    }
    const filterProductByCategory = async (category) => {
        await axios.get('/api/product/search/category/' + category)
        .then(res => setProducts(res.data))
    }
    const valueProvided = {
        searchPrediction,
        predictions,
        setPredictions,
        filterProductsBySearchTerm,
        filterProductByCategory,
        filter,
        setFilter
    }
    return <FilterContext.Provider value={valueProvided}>
        {children}
    </FilterContext.Provider>

}
export {FilterProvider};
export default FilterContext;
