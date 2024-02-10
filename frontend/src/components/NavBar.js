import { Link } from 'react-router-dom'
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import OrdersPage from '../pages/OrdersPage';
import AccountPage from '../pages/AccountPage';
import AboutPage from '../pages/AboutPage';
import CartPage from '../pages/CartPage';
import { BsCart4 } from "react-icons/bs";
import { FaSearch } from "react-icons/fa";
import { MdOutlineNotifications } from "react-icons/md";

export default function NavBar() {
    return (
        <div className='nav-container'>
            <div>
                <Link className='nav-bar-item' to={'/home'} element={<HomePage />}>
                    Home
                </Link>
            </div>

            <div className='nav-search-bar-container'>
                <input className='nav-search-bar' type='text' />
                <div className='nav-search-bar-icon'>
                    <FaSearch />
                </div>
            </div>
            <div className='nav-item-container' >
                <MdOutlineNotifications className='nav-bar-item' />
                <Link className='nav-bar-item' to={'/login'} element={<LoginPage />}>Sign-out</Link>
                <Link className='nav-bar-item' to={'/orders'} element={<OrdersPage />}>My Orders</Link>
                <Link className='nav-bar-item' to={'/account'} element={<AccountPage />}>Account</Link>
                <Link className='nav-bar-item' to={'/about'} element={<AboutPage />}>About</Link>
                <Link className='nav-bar-item' to={'/cart'} element={<CartPage />}><BsCart4 /></Link>
            </div>
        </div>
    )
}