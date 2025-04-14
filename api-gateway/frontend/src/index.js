import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.js";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import { ProductProvider } from "./context/ProductContext.js";
import { FilterProvider } from "./context/FilterContext.js";
import { Provider, useDispatch } from "react-redux";
import axios from "axios";
import {
  store,
} from "./store/index.js";
import "./mobile.css";
import { CartProvider } from "./context/cartContext.js";
async function getdata() {
  try {
    const res = await axios.get("/api/auth/user");
    return {
      token: res.data.token,
      fname: res.data.firstname,
      lname: res.data.lastname,
      email: res.data.email,
      address: res.data.address,
    };
  } catch (error) {}
}
const el = document.getElementById("root");
const root = ReactDOM.createRoot(el);

root.render(
  <Provider store={store}>
    <BrowserRouter>
      <ProductProvider>
        <FilterProvider>
          <CartProvider>
            <App data={await getdata()} />
          </CartProvider>
        </FilterProvider>
      </ProductProvider>
    </BrowserRouter>
  </Provider>
);
