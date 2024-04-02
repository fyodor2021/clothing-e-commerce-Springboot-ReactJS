import React, { useEffect } from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.js'
import { BrowserRouter, useNavigate } from 'react-router-dom'
import './index.css'
import { ProductProvider } from './context/ProductContext.js'
import { AuthProvider } from './context/AuthContext.js'
import { ReviewProvider } from './context/ReviewContext.js'
import { CartProvider } from './context/CartContext.js'
import { ValidationProvider } from './context/ValidationContext.js'
import { PaymentProvider } from './context/PaymentContext.js'
import { FilterProvider } from './context/FilterContext.js'
import { useCookies } from 'react-cookie'
import { OrderProvider } from './context/OrderContext.js'
import axios from 'axios'
const el = document.getElementById('root')
const root = ReactDOM.createRoot(el)
axios.defaults.baseURL = "http://localhost:8181/";

let indextoken
indextoken = window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
root.render(
    <BrowserRouter>
        <ValidationProvider>
            <AuthProvider>
                <CartProvider>
                    <ProductProvider>
                        <ReviewProvider>
                            <PaymentProvider>
                                <OrderProvider>
                                    <FilterProvider>
                                        <App indexToken={indextoken} />
                                    </FilterProvider>
                                </OrderProvider>
                            </PaymentProvider>
                        </ReviewProvider>
                    </ProductProvider>
                </CartProvider>
            </AuthProvider>
        </ValidationProvider>
    </BrowserRouter>
)