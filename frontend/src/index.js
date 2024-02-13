import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.js'
import { BrowserRouter } from 'react-router-dom'
import './index.css'
import { Provider } from './context/ProductContext.js'
const el = document.getElementById('root')


const root = ReactDOM.createRoot(el)

root.render(
    <Provider>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </Provider>
)