import { Link } from "react-router-dom";

export default function CatLinks() {
    return <div className='cat-container'>
            <Link className='cat-bar-item cat-home' to={'/'}>Home</Link>
            <Link className='cat-bar-item' to={'/women'}>Women</Link>
            <Link className='cat-bar-item' to={'/men'}>Men</Link>
            <Link className='cat-bar-item' to={'/kids'}>Kids</Link>
            <Link className='cat-bar-item' to={'/all'}>All</Link>
        </div>

} 