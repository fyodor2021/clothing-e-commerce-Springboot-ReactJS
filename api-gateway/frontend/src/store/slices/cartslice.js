import { createSlice } from "@reduxjs/toolkit";

export const cartSlice  = createSlice({
    name: 'cartSlice',
    initialState: {
        cartItems: [],
        cartItemCounter: 0
    },
    reducers: {
        setCartItems: (state,action) => {
            state.cartItems = action.payload
        },
        setCartItemCounter: (state,action) => {
            state.cartItemCounter = action.payload
        },
      
    }

})
export const cartReducer = cartSlice.reducer;
export const { setCartItems, setCartItemCounter} = cartSlice.actions;