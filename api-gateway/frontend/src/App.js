import NavBar from "./components/NavBar";
import DepartNav from "./components/CatLinks";
import { Routes, Route, useLocation, useNavigate } from "react-router-dom";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import AboutPage from "./pages/AboutPage";
import OrdersPage from "./pages/OrdersPage";
import AccountPage from "./pages/AccountPage";
import CartPage from "./pages/CartPage";
import DetailsPage from "./pages/DetailsPage";
import RegistrationPage from "./pages/RegistrationPage";
import CheckoutPage from "./pages/CheckoutPage";
import ProductPage from "./pages/ProductPage";
import Loading from "./components/Loading";
import {
  useFetchProductsQuery,
  setToken,
  setLoggedEmail,
  setLoggedFname,
  setLoggedLname,
  useLazyFetchCartProductsQuery,
  setCartItems,
  setLoggedAddress,
  setCartItemCounter,
} from "./store";
import { useDispatch } from "react-redux";
import { useEffect, useState } from "react";
import OrderDetailsPage from "./pages/OrderDetailsPage";
import axios from "axios";
export default function App({ data }) {
  const [token, setLocalToken] = useState();
  const dispatch = useDispatch();
  const location = useLocation();
  const navigate = useNavigate();
  const { isLoading } = useFetchProductsQuery();
  const [fetchCartProducts, fetchCartProductsResult] =
    useLazyFetchCartProductsQuery();




  useEffect(() => {
    if (data) {
      dispatch(setToken(data.token));
      setLocalToken(data.token);
      dispatch(setLoggedFname(data.fname));
      dispatch(setLoggedLname(data.lname));
      dispatch(setLoggedEmail(data.email));
      dispatch(setLoggedAddress(data.address));

    }
      const cart = window.localStorage.getItem("cart");
      let storedCart = cart ? JSON.parse(cart) : [];
      let cartItemsCount = 0;
      for (let cartItem of storedCart) {
        cartItemsCount += cartItem.quantity;
      }
      dispatch(setCartItemCounter(cartItemsCount));
  }, []);




  useEffect(() => {
    if (fetchCartProductsResult.isSuccess) {
      const storedCart = window.localStorage.getItem("cart");
      const storedCartItems = storedCart ? JSON.parse(storedCart) : [];

      const storedCartItemsCount =
        storedCartItems.length > 0 &&
        storedCartItems.reduce((acc, item) => {
          return acc + item.quantity;
        }, 0);

      dispatch(setCartItemCounter(storedCartItemsCount));
    }
  }, [fetchCartProductsResult]);




  useEffect(() => {
    if (location.pathname === "/login" && token) {
      navigate("/account");
    } else if (location.pathname === "/register" && token) {
      navigate("/account");
    } else if (location.pathname === "/account" && !token) {
      navigate("/login");
    } else if (location.pathname === "/checkout" && !token) {
      navigate("/login");
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
