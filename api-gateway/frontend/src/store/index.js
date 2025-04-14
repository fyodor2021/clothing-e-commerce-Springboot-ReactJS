import { configureStore } from "@reduxjs/toolkit";
import {productApi} from './apis/productApi'
import { setupListeners } from "@reduxjs/toolkit/query";
import { cartApi } from "./apis/cartApi";
import { authApi } from "./apis/authApi";
import { tokenReducer } from "./slices/tokenSlice";
import { paymentApi } from "./apis/paymentApi.js";
import { menuReducer } from "./slices/menuSlice.js";
import { orderApi } from "./apis/orderApi.js";
import { inputReducer } from "./slices/inputSlice.js";
import {validationReducer} from './slices/validationSlice.js'
import { searchApi } from "./apis/searchApi.js";
import { userReducer } from "./slices/userSlice.js";
import { cartReducer } from "./slices/cartslice.js";
import { useNavigate } from "react-router-dom";
// const authMiddleware = (store) => (next) => (action) => {
//     if(action.payload && action.payload.status){
//         if (action.type.endsWith('/rejected') && action.payload.status === 401) {
//             navigateToLogin()
//         }
//     }
//     return next(action);
// };
const store  = configureStore({
    reducer:{
        token: tokenReducer,
        user: userReducer,
        validation:validationReducer,
        input:inputReducer,
        menu: menuReducer,
        cartSlice:cartReducer,
        [authApi.reducerPath]: authApi.reducer,
        [productApi.reducerPath]: productApi.reducer,
        [cartApi.reducerPath]: cartApi.reducer,
        [paymentApi.reducerPath]: paymentApi.reducer,
        [orderApi.reducerPath]: orderApi.reducer,
        [searchApi.reducerPath]: searchApi.reducer,
    },
    middleware: (getDefaultMiddleware) => {
        return getDefaultMiddleware({
            ignoredPaths: ['input'],
        })
        .concat(authApi.middleware)
        .concat(productApi.middleware)
        .concat(cartApi.middleware)
        .concat(paymentApi.middleware)
        .concat(orderApi.middleware)
        .concat(searchApi.middleware)
    }
})

setupListeners(store.dispatch)
export {store}

export {useRegisterMutation, 
    useLoginMutation, 
    useLoggedUserQuery,
    useUpdateUserMutation,useSignoutMutation} from './apis/authApi'
export {useFetchProductsQuery,useFetchProductDetailsQuery, useLazyFetchCartProductsQuery} from './apis/productApi'
export {
    useAddToCartMutation,
    useProductCountDecrementMutation,
    useProductCountIncrementMutation,
    useEmptyCartMutation,
    } from './apis/cartApi'
export { useAddPaymentMethodMutation, useFetchCardsInfoQuery, useDeleteCardInfoMutation} from './apis/paymentApi.js'
export { usePlaceOrderMutation, useCancelOrderMutation, useGetLoggedUserOrdersQuery, useLazyGetOrderByOrderIdQuery } from './apis/orderApi.js'
export {setResMessage} from './slices/validationSlice.js'
export { setToken }  from './slices/tokenSlice.js'
export { setMenu }  from './slices/menuSlice.js'
export { setEmail, 
    setFname, 
    setLname, 
    setPassword, 
    setPasswordRetype, 
    setAddress,
setCardNumber,
setSecurityCode,
setExpire} from './slices/inputSlice.js'
export {
    setLoggedFname,
    setLoggedLname,
    setLoggedEmail,
    setLoggedAddress
} from './slices/userSlice.js'
export { useLazyFilterProductsBySearchTermQuery } from './apis/searchApi.js' 
export { setCartItems,setCartItemCounter} from './slices/cartslice.js'
