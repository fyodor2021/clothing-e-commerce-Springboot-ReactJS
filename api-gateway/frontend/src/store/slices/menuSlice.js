import { createSlice } from "@reduxjs/toolkit";


export const menuSlice  = createSlice({
    name: 'token',
    initialState: {
        value: false
    },
    reducers: {
        setMenu: (state,action) => {
            state.value = action.payload
        }
    }
})
export const menuReducer = menuSlice.reducer;
export const { setMenu} = menuSlice.actions;