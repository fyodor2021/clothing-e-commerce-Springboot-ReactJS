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
import RegistrationPage from "./pages/RegistrationPage";
import AddProductPage from "./pages/AddProductPage";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import CheckoutPage from "./pages/CheckoutPage";
import { useCookies } from "react-cookie";
export default function App({ indexToken }) {
    const navigate = useNavigate();
    const [cookies, setCookie, removeCookie] = useCookies()

    if (cookies['SIGNOUT']) {
        window.localStorage.removeItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
        window.localStorage.removeItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL_EXPIRATION)
        removeCookie(['SIGNOUT'])
    }
    axios.interceptors.request.use((request) => {
        axios.defaults.withCredentials = true
        const token = window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
        if (token) {
            if (Date.parse(window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL_EXPIRATION)) < new Date()) {
                window.localStorage.removeItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
                window.localStorage.removeItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL_EXPIRATION)
            }
            request.headers.Authorization = `Bearer ${token}`
        } else {
            return request;
        }
        return request;
    })

    axios.interceptors.response.use(response => {
        return response;
    }, error => {
        if (error.response && error.response.status == 403) {
            navigate("/login")
        }
    })

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
            <Route path='/checkout' element={<CheckoutPage />} />
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