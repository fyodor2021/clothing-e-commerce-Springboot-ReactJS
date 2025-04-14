import { createSlice } from "@reduxjs/toolkit";


export const validationSlice  = createSlice({
    name: 'validation',
    initialState: {
        resMessage: ''
    },
    reducers: {
        setResMessage: (state,action) => {
            state.resMessage = action.payload
        }
    }
})
export const validationReducer = validationSlice.reducer;
export const {setResMessage} = validationSlice.actions;