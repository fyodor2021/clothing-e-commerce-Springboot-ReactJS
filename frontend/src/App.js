import NavBar from "./components/NavBar";
import DepartNav from './components/CatLinks'
import { Routes, Route } from 'react-router-dom'
import HomePage from "./pages/HomePage";
import LoginPage from './pages/LoginPage'
import AboutPage from "./pages/AboutPage";
import OrdersPage from "./pages/OrdersPage";
import AccountPage from "./pages/AccountPage";
import CartPage from "./pages/CartPage";
import DetailsPage from './pages/DetailsPage'
import { useContext, useEffect } from "react"
import RegistrationPage from "./pages/RegistrationPage";
import AddProductPage from "./pages/AddProductPage";
import useGeneralContext from "./hooks/useGeneralContext";
import useProductContext from "./hooks/useProductContext";
import useCartContext from "./hooks/useCartContext";
export default function App() {
    const {fetchProducts} = useProductContext()
    const {createCart} = useCartContext()
    const {getCookie} = useGeneralContext()
    useEffect(() => {
         fetchProducts();
         const cartId = getCookie('cart')
         if(!cartId){
             createCart();
         }
    },[])
    return <>
        <NavBar />
        <DepartNav />
        <Routes>
            <Route path='/' element={<HomePage />} />
            <Route path='/about' element={<AboutPage />} />
            <Route path='/orders' element={<OrdersPage />} />
            <Route path='/login' element={<LoginPage />} />
            <Route path='/register' element={<RegistrationPage />} />
            <Route path='/account' element={<AccountPage />} />
            <Route path='/details/:productId' element={<DetailsPage />} />
            <Route path='/add-product' element={<AddProductPage />} />
            <Route path='/cart' element={<CartPage />} />
            <Route path='/meat' element={<HomePage />}>Meat</Route>
            <Route path='/cafe' element={<HomePage />}>Cafe</Route>
            <Route path='/deli' element={<HomePage />}>Deli</Route>
            <Route path='/nuts' element={<HomePage />}>Nuts</Route>
            <Route path='/sweets' element={<HomePage />}>Sweets</Route>
            <Route path='/bread' element={<HomePage />}>Bread</Route>
            <Route path='/grocery' element={<HomePage />}>Grocery</Route>
            <Route path='/catering' element={<HomePage />}>Catering</Route>
            <Route path='/arz-brand' element={<HomePage />}>Arz Brand</Route>
            <Route path='/produce' element={<HomePage />}>Produce</Route>
        </Routes>

    </>
}