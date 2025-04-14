import { createSlice } from "@reduxjs/toolkit";
export const userSlice  = createSlice({
    name: 'user',
    initialState: {
        fname: '',
        lname: '',
        email: '',
        address:''
    },
    reducers: {
        setLoggedFname: (state,action) => {
            state.fname = action.payload
        },
        setLoggedLname: (state,action) => {
            state.lname = action.payload
        },
        setLoggedEmail: (state,action) => {
            state.email = action.payload
        },
        setLoggedAddress: (state,action) => {
            state.address = action.payload
        }
    }
})
export const userReducer = userSlice.reducer;
export const { setLoggedFname,setLoggedLname,setLoggedEmail,setLoggedAddress} = userSlice.actions;