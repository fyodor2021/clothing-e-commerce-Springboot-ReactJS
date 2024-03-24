import { Link,useNavigate } from 'react-router-dom'
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import OrdersPage from '../pages/OrdersPage';
import AccountPage from '../pages/AccountPage';
import AboutPage from '../pages/AboutPage';
import CartPage from '../pages/CartPage';
import { BsCart4 } from "react-icons/bs";
import { FaSearch } from "react-icons/fa";
import { MdOutlineNotifications } from "react-icons/md";
import { GiHamburgerMenu } from "react-icons/gi";
import { useEffect, useState } from 'react'
import arzBrand from '../statics/arz-brand.png'
import userAvatar from '../statics/user-avatar.png'
import useAuthContext from '../hooks/useAuthContext';
export default function NavBar() {
    const [menu, setMenu] = useState(false)
    const [notificationPanel, setNotificationPanel] = useState(false);
    const navigate = useNavigate();
    const handleMenuToggle = () => {
        setMenu(!menu)
    }
    const handleNotificationExpand = () => {
        setNotificationPanel(!notificationPanel)
    }
    const handleSignout = () => {
        window.localStorage.removeItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
        window.location.replace('/')
    }
    const notiItem = <div>
        <div className='noti-user-avatar'>
            <img src={userAvatar} />
        </div>
        <div>
            <div className='text-2xl'>
                username
            </div>
            <div>
                Please disregard the content of this message as it does not
                contain any real information. "Chat GPT.."
            </div>
        </div>
    </div>
    return (
        <div className='nav-container'>
            <div>
                <Link className='nav-bar-item' to={'/'} element={<HomePage />}>
                    <img className="arz-logo-home" src={arzBrand} />
                    <img className="arz-logo-home-hidden" src={arzBrand} />
                </Link>

                <div className='hamburger-menu-icon' onClick={handleMenuToggle}>
                    <GiHamburgerMenu />
                </div>
            </div>

            <div className='nav-search-bar-container'>
                <input className='nav-search-bar' type='text' />
                <div className='nav-search-bar-icon'>
                    <FaSearch />
                </div>
            </div>
            <div className='nav-item-container' >
                <MdOutlineNotifications className='nav-bar-item' onClick={handleNotificationExpand} />
{
    window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL) ? 
    <Link className=' nav-bar-item-signout nav-bar-item' onClick={handleSignout}>Sign-out</Link> :
    <Link className='nav-bar-item' to={'/login'} element={<LoginPage />}>Login</Link>
}
                <Link className='nav-bar-item' to={'/orders'} element={<OrdersPage />}>My Orders</Link>
                <Link className='nav-bar-item' to={'/account'} element={<AccountPage />}>Account</Link>
                <Link className='nav-bar-item' to={'/about'} element={<AboutPage />}>About</Link>
                <Link className='nav-bar-item' to={'/cart'} element={<CartPage />}><BsCart4 /></Link>
            </div>
            {notificationPanel ?
                <div className='notification-panel'>
                    {notiItem}
                    {notiItem}
                    {notiItem}
                    {notiItem}
                    {notiItem}
                    {notiItem}
                    {notiItem}

                </div> : ''}
            {menu ? <div className='navigation-menu'>
                <div>
                    <button className='button'>sign in</button>
                </div>
                <div>
                    <button className='button'>My orders</button>
                </div>
                <div>
                    <button className='button'>Account</button>

                </div>
                <div>
                    <button className='button'>About</button>

                </div>
                <div></div>
                <div></div>
            </div> : ''}
            <Link className='nav-bar-item hamburger-menu-icon' to={'/cart'} element={<CartPage />}><BsCart4 /></Link>

        </div>
    )
}