import { useEffect, useRef, useState } from 'react';
import { BsCart4 } from 'react-icons/bs';
import { CiMenuBurger } from 'react-icons/ci';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import useFilterContext from '../hooks/useFilterContext';
import AboutPage from '../pages/AboutPage';
import AccountPage from '../pages/AccountPage';
import CartPage from '../pages/CartPage';
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import OrdersPage from '../pages/OrdersPage';
import josedorBranding from '../statics/josedor-branding.png';
import {
  setMenu,
  useSignoutMutation,
  useLazyFetchProductsBySearchTermQuery,
} from '../store';
export default function NavBar() {
  const [showSearch, setShowSearch] = useState(false);
  const [searchTerm, setSearchTerm] = useState();
  const [fetchProductsBySearchTerm, fetchProductsBySearchTermResult] =
    useLazyFetchProductsBySearchTermQuery();

  const dispatch = useDispatch();
  const { value: menu } = useSelector((state) => {
    return state.menu;
  });
  const { value: token } = useSelector((state) => {
    return state.token;
  });
  const { cartItemCounter } = useSelector((state) => {
    return state.cartSlice;
  });
  const { cartItems } = useSelector((state) => {
    return state.cartSlice;
  });
  const [signout, signoutResults] = useSignoutMutation();
  const loggedUser = useSelector((state) => {
    return state.user;
  });
  const navigate = useNavigate();
  const searchResult = useRef();
  const notiPanel = useRef();

  useEffect(() => {
    const handler = (e) => {
      if (searchResult && !searchResult.current.contains(e.target)) {
        setShowSearch(false);
      }
    };
    document.addEventListener('mousedown', handler);
    return () => {
      document.removeEventListener('mousedown', handler);
    };
  }, []);

  useEffect(() => {
    if (signoutResults.isSuccess) {
      window.localStorage.removeItem('cart');
      navigate(0);
    }
  }, [signoutResults]);

  useEffect(() => {
    if (fetchProductsBySearchTermResult.isSuccess) {
      dispatch(setMenu(false));
    }
  }, [fetchProductsBySearchTermResult]);
  const handleSearchFocus = () => {
    setShowSearch(true);
  };
  const handleMenuToggle = () => {
    dispatch(setMenu(!menu));
  };

  const handleSignout = () => {
    const storedCart = window.localStorage.getItem('cart');
    const cartItems = storedCart ? JSON.parse(storedCart) : [];

    const signoutReq = {
      products: cartItems.length > 0 ? cartItems : [],
      userEmail: loggedUser.email,
    };
    signout(signoutReq);
  };
  const handleSearchSubmit = (event) => {
    event.preventDefault();
    setShowSearch(false);
    navigate('/product/details/' + fetchProductsBySearchTermResult.data[0].productId);
  };

  const handleSearchBoxChange = (event) => {
    fetchProductsBySearchTerm(event.target.value.replace(/[^\w\s]/gi, ''));
  };

  const handleSearchTermClick = async (item) => {
    setShowSearch(false);
    navigate('/product/details/' + item.productId);
  };
  let renderedItems;

  renderedItems =
    fetchProductsBySearchTermResult.data &&
    fetchProductsBySearchTermResult.data.slice(0, 6).map((item, key) => {
      const modedString = item.productName.replace(
        new RegExp(searchTerm, 'gi'),
        (match) => `<b>${match}</b>`
      );
      return (
        <div
          onClick={() => handleSearchTermClick(item)}
          className="search-rendered-item"
          key={key}
        >
          <div dangerouslySetInnerHTML={{ __html: modedString }}></div>
        </div>
      );
    });

  return (
    <div className="nav-container">
      {/* logo image */}
      <Link className="nav-bar-item" to={'/'} element={<HomePage />}>
        <img className="josedor-logo-home" src={josedorBranding} />
      </Link>
      {/* search input  */}
      <div className="flex items-center w-full" ref={searchResult}>
        <form onSubmit={handleSearchSubmit} className=" w-full flex-1">
          <input
            onFocus={handleSearchFocus}
            onChange={handleSearchBoxChange}
            placeholder="Search"
            className="nav-search-bar text-black w-full"
            type="text"
            value={searchTerm}
          />
          {showSearch ? (
            <div className="rendered-search text-black">{renderedItems}</div>
          ) : (
            ''
          )}
        </form>
        <CiMenuBurger
          className="text-3xl flex xl:hidden w-[20px]"
          onClick={handleMenuToggle}
        />
      </div>
      {/* navigation bar items  */}
      <div className="nav-item-container hidden xl:flex" ref={notiPanel}>
        {token ? (
          <Link
            className=" nav-bar-item-signout nav-bar-item"
            onClick={handleSignout}
          >
            Sign-out
          </Link>
        ) : (
          <Link className="nav-bar-item" to={'/login'} element={<LoginPage />}>
            Login
          </Link>
        )}
        <Link className="nav-bar-item" to={token ? '/orders' : '/login'}>
          My Orders
        </Link>
        {token ? (
          <Link
            className="nav-bar-item"
            to={'/account'}
            element={<AccountPage />}
          >
            Account
          </Link>
        ) : (
          ''
        )}
        <Link className="nav-bar-item" to={'/about'} element={<AboutPage />}>
          About
        </Link>
        <Link
          className="nav-bar-item relative"
          to={'/cart'}
          element={<CartPage />}
        >
          {cartItemCounter > 0 ? (
            <span
              className="absolute top-[-12px] right-[-12px] z-[10] w-[20px] 
            h-[20px] flex justify-center items-center text-[.75rem] rounded-full p-1 bg-red-500 text-white"
            >
              {cartItemCounter}
            </span>
          ) : (
            ''
          )}
          <span className="cart-icon">
            <BsCart4 />
          </span>
        </Link>
      </div>
      {/* hamburger menu */}
      <div className={`navigation-menu ${menu ? 'show' : ''}`}>
        <div className="navigation-menu-item">
          <Link
            to={token ? '/orders' : '/login'}
            element={<OrdersPage />}
            onClick={() => dispatch(setMenu(false))}
          >
            My Orders
          </Link>
        </div>
        {token ? (
          <div className="navigation-menu-item">
            <Link
              to={'/account'}
              element={<AccountPage />}
              onClick={() => dispatch(setMenu(false))}
            >
              Account
            </Link>
          </div>
        ) : (
          ''
        )}
        <div className="navigation-menu-item">
          <Link
            to={'/about'}
            element={<AboutPage />}
            onClick={() => dispatch(setMenu(false))}
          >
            About
          </Link>
        </div>

        {token ? (
          <div className="navigation-menu-item">
            <Link className=" nav-bar-item-signout" onClick={handleSignout}>
              Sign-out
            </Link>
          </div>
        ) : (
          <div className="navigation-menu-item">
            <Link
              to={'/login'}
              element={<LoginPage />}
              onClick={() => dispatch(setMenu(false))}
            >
              Login
            </Link>
          </div>
        )}
        <div className="navigation-menu-item">
          <Link
            to={'/cart'}
            style={{ position: 'relative' }}
            element={<CartPage />}
            onClick={() => dispatch(setMenu(false))}
          >
            {cartItemCounter > 0 ? (
              <span
                className="absolute top-[-12px] left-[12px] z-[10] w-[20px] 
            h-[20px] flex justify-center items-center text-[.75rem] rounded-full p-1 bg-red-500 text-white"
              >
                {cartItemCounter}
              </span>
            ) : (
              ''
            )}
            <span className="cart-icon">
              <BsCart4 />
            </span>
          </Link>
        </div>
      </div>
    </div>
  );
}
