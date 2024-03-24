import React, { useEffect } from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.js'
import { BrowserRouter, useNavigate } from 'react-router-dom'
import './index.css'
import { ProductProvider } from './context/ProductContext.js'
import { AuthProvider } from './context/AuthContext.js'
import { ReviewProvider } from './context/ReviewContext.js'
import { CartProvider } from './context/CartContext.js'
import { GeneralProvider } from './context/GeneralContext.js'
import axios from 'axios'
const el = document.getElementById('root')
const root = ReactDOM.createRoot(el)
axios.defaults.baseURL = "http://localhost:8181/";
let indextoken 
    indextoken = window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
root.render(
    <BrowserRouter>
        <GeneralProvider>
            <AuthProvider>
                <CartProvider>
                    <ProductProvider>
                        <ReviewProvider>
                            <App indexToken={indextoken}/>
                        </ReviewProvider>
                    </ProductProvider>
                </CartProvider>
            </AuthProvider>
        </GeneralProvider>
    </BrowserRouter>
)