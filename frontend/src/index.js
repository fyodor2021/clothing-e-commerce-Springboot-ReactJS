import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.js'
import { BrowserRouter } from 'react-router-dom'
import './index.css'
import { ProductProvider } from './context/ProductContext.js'
import { UserProvider } from './context/UserContext.js'
import { ReviewProvider } from './context/ReviewContext.js'
import { CartProvider } from './context/CartContext.js'
import { GeneralProvider } from './context/GeneralContext.js'

const el = document.getElementById('root')


const root = ReactDOM.createRoot(el)

root.render(
    <GeneralProvider>
        <UserProvider>
            <CartProvider>
                <ProductProvider>
                    <ReviewProvider>
                        <BrowserRouter>
                            <App />
                        </BrowserRouter>
                    </ReviewProvider>
                </ProductProvider>
            </CartProvider>
        </UserProvider>
    </GeneralProvider>
)