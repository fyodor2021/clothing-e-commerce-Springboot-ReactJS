import { useNavigate, useNavigation } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import {useState } from 'react'
import useFilterContext from '../hooks/useFilterContext';
export default function CatLinks() {
    const navigate = useNavigate();
    const {filterProductByCategory} = useFilterContext();
    const handleCategroyClick = (department) => {
        filterProductByCategory(department)
    }
    const handleAddProductClick = () => {
        navigate('/add-product')
    }
    return <div>
            <div  className='cat-container'>
                <span className='cat-bar-item'onClick={() => handleCategroyClick('meat')}>Meat</span>
                <span className='cat-bar-item'  to={'/cafe'} onClick={() => handleCategroyClick('cafe')}>Cafe</span>
                <span className='cat-bar-item'  to={'/deli'} onClick={() => handleCategroyClick('deli')}>Deli</span>
                <span className='cat-bar-item'  to={'/nuts'} onClick={() => handleCategroyClick('nuts')}>Nuts</span>
                <span className='cat-bar-item'  to={'/sweets'} onClick={() => handleCategroyClick('sweets')}>Sweets</span>
                <span className='cat-bar-item'  to={'/bread'} onClick={() => handleCategroyClick('bread')}>Bread</span>
                <span className='cat-bar-item'  to={'/grocery'} onClick={() => handleCategroyClick('grocery')}>Grocery</span>
                <span className='cat-bar-item'  to={'/catering'} onClick={() => handleCategroyClick('catering')}>Catering</span>
                <span className='cat-bar-item'  to={'/arz-brand'} onClick={() => handleCategroyClick('arz-Brand')}>Arz Brand</span>
                <span className='cat-bar-item'  to={'/produce'} onClick={() => handleCategroyClick('produce')}>Produce</span>
            </div>

    </div>
} 