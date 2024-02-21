import { useNavigate, useNavigation } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import {useState } from 'react'
export default function CatLinks() {
    const [cat, setCat]  = useState('');
    const navigate = useNavigate()
    const handleCategroyClick = (department) => {
        setCat(department)
    }
    const handleAddProductClick = () => {
        navigate('/add-product')
    }
    return <div>
            <div  className='cat-container'>

                <span className='cat-bar-item'onClick={() => handleAddProductClick('product')}>Add Product</span>
                <span className='cat-bar-item'onClick={() => handleCategroyClick('meat')}>Meat</span>
                <span className='cat-bar-item'  to={'/cafe'} onClick={() => handleCategroyClick('Cafe')}>Cafe</span>
                <span className='cat-bar-item'  to={'/deli'} onClick={() => handleCategroyClick('Deli')}>Deli</span>
                <span className='cat-bar-item'  to={'/nuts'} onClick={() => handleCategroyClick('Nuts')}>Nuts</span>
                <span className='cat-bar-item'  to={'/sweets'} onClick={() => handleCategroyClick('Sweets')}>Sweets</span>
                <span className='cat-bar-item'  to={'/bread'} onClick={() => handleCategroyClick('Bread')}>Bread</span>
                <span className='cat-bar-item'  to={'/grocery'} onClick={() => handleCategroyClick('Grocery')}>Grocery</span>
                <span className='cat-bar-item'  to={'/catering'} onClick={() => handleCategroyClick('Catering')}>Catering</span>
                <span className='cat-bar-item'  to={'/arz-brand'} onClick={() => handleCategroyClick('Arz Brand')}>Arz Brand</span>
                <span className='cat-bar-item'  to={'/produce'} onClick={() => handleCategroyClick('Produce')}>Produce</span>
            </div>

    </div>
}