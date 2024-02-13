export default function FilterPanel({ className, hidden }) {
    return <div className={"filter-panel-container " + className}>
        {!hidden ?
            <div>
                <div className="filter-item">sample-search-filter</div>
                <div className="filter-item">sample-search-filter</div>
                <div className="filter-item">sample-search-filter</div>
                <div className="filter-item">sample-search-filter</div>
                <div className="filter-item">sample-search-filter</div>
                <div className="filter-item">sample-search-filter</div>
                <div className="filter-item">sample-search-filter</div>
                <div className="filter-item">sample-search-filter</div>
            </div>
            :<div> 
                <div>sample-search-filter</div>
            </div>
        }

    </div>
}