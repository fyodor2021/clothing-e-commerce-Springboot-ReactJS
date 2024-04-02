import { useState } from "react"
import useFiltercontext from '../hooks/useFilterContext.js'
export default function FilterPanel({ className, hidden,  filterPanelRef}) {
    const {filter,setFilter}= useFiltercontext();
    const handleSorting = (filter) => {
            const trimedFilter = filter.replace(/\s/g,'');
            const modedFilterWord = trimedFilter.toLowerCase();
            setFilter(modedFilterWord)
    }
    return <div className={"filter-panel-container " + className}>
        {!hidden ?
            <div>
                <h1 className="filter-item-header filter-header">Search Filter</h1>
                <div className="filter-item-header">price range</div>
                <div onClick={() => handleSorting('High to low')} className="filter-item">High to low</div>
                <div onClick={() => handleSorting('Low to high')} className="filter-item">Low to high</div>
                <div className="filter-item-header">Contains</div>
                <div onClick={() => handleSorting('Meat')} className="filter-item">Meat</div>
                <div onClick={() => handleSorting('Non-dairy')} className="filter-item">Non-dairy</div>
                <div onClick={() => handleSorting('Vegan')} className="filter-item">Vegan</div>
                <div onClick={() => handleSorting('Nuts free')} className="filter-item">Nuts free</div>
                <div onClick={() => handleSorting('Gluten free')} className="filter-item">Gluten free</div>
                <div onClick={() => handleSorting('Organic')} className="filter-item">Organic</div>
                <div onClick={() => handleSorting('Shellfish')} className="filter-item">Shellfish</div>
            </div>
            : <div>
                <div>sample-search-filter</div>
            </div>
        }

    </div>
}