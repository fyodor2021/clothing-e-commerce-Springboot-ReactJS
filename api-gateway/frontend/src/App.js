import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { Route, Routes, useLocation, useNavigate } from 'react-router-dom';
import DepartNav from './components/CatLinks';
import Loading from './components/Loading';
import NavBar from './components/NavBar';
import useCartContext from './hooks/useCartContext';
import AboutPage from './pages/AboutPage';
import AccountPage from './pages/AccountPage';
import CartPage from './pages/CartPage';
import CheckoutPage from './pages/CheckoutPage';
import DetailsPage from './pages/DetailsPage';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import OrderDetailsPage from './pages/OrderDetailsPage';
import OrdersPage from './pages/OrdersPage';
import ProductPage from './pages/ProductPage';
import RegistrationPage from './pages/RegistrationPage';
import {
  setLoggedAddress,
  setLoggedEmail,
  setLoggedFname,
  setLoggedLname,
  setToken,
  useFetchProductsQuery,
} from './store';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function App({ data }) {
  const [token, setLocalToken] = useState();
  const dispatch = useDispatch();
  const location = useLocation();
  const navigate = useNavigate();
  const { isLoading } = useFetchProductsQuery();
  const { getStorageCartItems } = useCartContext();
  useEffect(() => {
    if (data) {
      dispatch(setToken(data.token));
      setLocalToken(data.token);
      dispatch(setLoggedFname(data.fname));
      dispatch(setLoggedLname(data.lname));
      dispatch(setLoggedEmail(data.email));
      dispatch(setLoggedAddress(data.address));
    }
    getStorageCartItems();
  }, []);

  // useEffect(() => {
  //   if (fetchCartProductsResult.isSuccess) {
  //     const storedCart = window.localStorage.getItem('cart');
  //     const storedCartItems = storedCart ? JSON.parse(storedCart) : [];

  //     const storedCartItemsCount =
  //       storedCartItems.length > 0 &&
  //       storedCartItems.reduce((acc, item) => {
  //         return acc + item.quantity;
  //       }, 0);
  //     dispatch(setCartItems(storedCart));
  //     dispatch(setCartItemCounter(storedCartItemsCount));
  //   }
  // }, [fetchCartProductsResult]);

  useEffect(() => {
    if (location.pathname === '/login' && token) {
      navigate('/account');
    } else if (location.pathname === '/register' && token) {
      navigate('/account');
    } else if (location.pathname === '/account' && !token) {
      navigate('/login');
    } else if (location.pathname === '/checkout' && !token) {
      navigate('/login');
    }
  }, [location]);

  return (
    <>
      <NavBar />
      <DepartNav />
      <div className="">
        {token ? (
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/about" element={<AboutPage />} />
            <Route path="/orders" element={<OrdersPage />} />
            <Route
              path="product/details/:productId"
              element={<DetailsPage />}
            />
            <Route path="/cart" element={<CartPage />} />
            <Route path="/women" element={<ProductPage />} />
            <Route path="/men" element={<ProductPage />} />
            <Route path="/kids" element={<ProductPage />} />
            <Route path="/all" element={<ProductPage />} />
            <Route path="/account" element={<AccountPage />} />
            <Route path="/checkout" element={<CheckoutPage />} />
            <Route path="/filter" element={<ProductPage />} />
            <Route
              path="order/details/:orderId"
              element={<OrderDetailsPage />}
            />
            <Route path="*" element={<HomePage />} />
          </Routes>
        ) : (
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/about" element={<AboutPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegistrationPage />} />
            <Route
              path="product/details/:productId"
              element={<DetailsPage />}
            />
            <Route path="/cart" element={<CartPage />} />
            <Route path="/women" element={<ProductPage />} />
            <Route path="/men" element={<ProductPage />} />
            <Route path="/kids" element={<ProductPage />} />
            <Route path="/all" element={<ProductPage />} />
            <Route path="/filter" element={<ProductPage />} />
            <Route path="*" element={<HomePage />} />
          </Routes>
        )}
      </div>
      {isLoading ? <Loading></Loading> : <></>}
    </>
  );
}
