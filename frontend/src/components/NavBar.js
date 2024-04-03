import { Link, useNavigate } from 'react-router-dom'
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
import { useEffect, useRef, useState } from 'react'
import arzBrand from '../statics/arz-brand.png'
import userAvatar from '../statics/user-avatar.png'
import useAuthContext from '../hooks/useAuthContext';
import useCartContext from '../hooks/useCartContext';
import useFilterContext from '../hooks/useFilterContext'
import useProductContext from '../hooks/useProductContext';
export default function NavBar() {
    const [menu, setMenu] = useState(false)
    const [notificationPanel, setNotificationPanel] = useState(false);
    const [showSearch, setShowSearch] = useState(false);
    const { cartProducts } = useCartContext();
    const { signout } = useAuthContext();
    const [searchTerm, setSearchTerm] = useState();
    const {setFilter} = useFilterContext();
    const { fetchProducts , setProducts} = useProductContext();
    const { searchPrediction, predictions, setPredictions, filterProductsBySearchTerm } = useFilterContext();
    const navigate = useNavigate();
    const searchResult = useRef();
    const notiPanel = useRef();
    useEffect(() => {
        const handler = (e) => {
            if(searchResult && !searchResult.current.contains(e.target)
            ){
                setShowSearch(false)
            }
            if(notiPanel && !notiPanel.current.contains(e.target)){
                setNotificationPanel(false)
            }

        }
        document.addEventListener('mousedown', handler)
        return () => {
        document.removeEventListener('mousedown', handler)

        }

    },[])

    const handleSearchFocus = () => {
        setShowSearch(true);
    }
    const handleMenuToggle = () => {
        setMenu(!menu)
    }
    const handleNotificationExpand = () => {
        setNotificationPanel(!notificationPanel)
    }
    const handleSignout = () => {
        signout();
    }
    const handleSearchSubmit = async (event) => {
        event.preventDefault();
        setShowSearch(false)
        // await filterProductsBySearchTerm(searchTerm)
        setFilter(searchTerm)
        navigate('/')
    }
    const handleHomeDirect = async () => {
        await fetchProducts();
        navigate("/");
    }
    let productCount;
    if (cartProducts) {
        productCount = cartProducts.reduce((acc, curr) => {
            return parseInt(acc, 10) + parseInt(curr.quantity, 10)
        }, [0])
    }

    let renderedItems;
    const handleSearchBoxChange = (event) => {
        setShowSearch(true)
        setSearchTerm(event.target.value)
        if (event.target.value) {
            searchPrediction(event.target.value)
        } else {
            setPredictions('')
        }
    }
    const handleSearchTermClick = async (item) => {
        setShowSearch(false)
        navigate('/details/' + item.productId)
    }
    if (predictions) {
        renderedItems = predictions.slice(0, 6).map((item, key) => {
            const modedString = item.productName.replace(new RegExp(searchTerm, "gi"), match => `<b>${match}</b>`);
            return <div onClick={() => handleSearchTermClick(item)} className='search-rendered-item' key={key}>
                <div dangerouslySetInnerHTML={{ __html: modedString }}></div>
            </div>
        });
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
                <Link className='nav-bar-item' onClick={handleHomeDirect} element={<HomePage />}>
                    <img className="arz-logo-home" src={arzBrand} />
                    <img className="arz-logo-home-hidden" src={arzBrand} />
                </Link>
            </div>
            <div className='nav-search-bar-container' ref={searchResult}>
                <form onSubmit={handleSearchSubmit}>
                    <input onFocus={handleSearchFocus} onChange={handleSearchBoxChange} className='nav-search-bar' type='text' value={searchTerm} />
                    <div className='nav-search-bar-icon'>
                        <FaSearch />
                    </div>
                    {showSearch ? <div className='rendered-search'>
                {renderedItems}
            </div> : ''}
                </form>

            </div>
            <div className='nav-item-container' ref={notiPanel}>
                <MdOutlineNotifications className='nav-bar-item' onClick={handleNotificationExpand} />
                {
                    window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL) ?
                        <Link className=' nav-bar-item-signout nav-bar-item' onClick={handleSignout}>Sign-out</Link> :
                        <Link className='nav-bar-item' to={'/login'} element={<LoginPage />}>Login</Link>
                }
                <Link className='nav-bar-item' to={'/orders'} element={<OrdersPage />}>My Orders</Link>
                {window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL) ? <Link className='nav-bar-item' to={'/account'} element={<AccountPage />}>Account</Link> : ''}
                <Link className='nav-bar-item' to={'/about'} element={<AboutPage />}>About</Link>
                <Link className='nav-bar-item' to={'/cart'} element={<CartPage />}>
                    {productCount != 0 ? <span className='cart-product-count' style={{ backgroundColor: "red" }} >{productCount}</span> : ''}
                    <span className='cart-icon'><BsCart4 /></span>
                </Link>
            </div>
            {notificationPanel ?
                <div  className='notification-panel' >
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

        </div>
    )
}