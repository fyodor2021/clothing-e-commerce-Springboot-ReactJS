import { createSlice } from "@reduxjs/toolkit";


export const inputSlice = createSlice({
    name: 'input',
    initialState: {
        email: '',
        emailVal: false,
        emailValMes: '',
        fname: '',
        fnameVal: false,
        fnameValMes: '',
        lname: '',
        lnameVal: false,
        lnameValMes: '',
        address: '',
        addressVal: false,
        addressValMes: '',
        password: '',
        passwordVal: false,
        passwordValMes: '',
        passwordRetype: '',
        passwordRetypeVal: false,
        passwordRetypeValMes: '',
        cardNumber:'',
        cardNumberVal: false,
        cardNumberValMes: '',
        securityCode:'',
        securityCodeVal:false,
        securityCodeValMes:'',
        expire: '',
        expireVal: false,
        expireValMes: '',
    },

    reducers: {
        setEmail: (state, action) => {
            state.email = action.payload.toLowerCase();
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if(emailRegex.test(state.email)){
                state.emailVal = true
                state.emailValMes = ''

            }else{
                state.emailVal = false
                state.emailValMes = 'please enter a valid email'
            }
        },
        setFname: (state, action) => {
            state.fname = action.payload
            if(/^[a-zA-Z]+$/.test(state.fname) && state.fname.length > 4){
                state.fnameVal = true
                state.fnameValMes = ''

            }else{
                state.fnameVal = false
                state.fnameValMes = 'please enter a valid first name'

            }
        },
        setLname: (state, action) => {
            state.lname = action.payload
            if(/^[a-zA-Z]+$/.test(state.lname) && state.lname.length > 4){
                state.lnameVal = true
                state.lnameValMes = ''

            }else{
                state.lnameVal = false
                state.lnameValMes = 'please enter a valid last name'

            }
        },
        setAddress: (state,action) => {
            state.address = action.payload;
            const addressRegex = /^(\d+) ?([A-Za-z](?= ))? (.*?) ([^ ]+?) ?((?<= )APT)? ?((?<= )\d*)?$/;
            console.log(addressRegex.test(state.address))
            if(addressRegex.test(state.address)){
                state.addressVal = true
                state.addressValMes = ''

            }else{
                state.addressVal = false
                state.addressValMes = 'please choose an address from the list'
            }
        },
        setPassword: (state, action) => {
            state.password = action.payload
            if(state.password.length >= 8){
                state.passwordVal = true
                state.passwordValMes = ''
                if(state.passwordRetype === state.password){
                    state.passwordRetypeVal = true
                }else{
                    state.passwordRetypeVal = false
                }
            }else{
                state.passwordVal = false
                state.passwordValMes = 'please enter a valid password'
            }
        },
        setPasswordRetype: (state, action) => {
            state.passwordRetype = action.payload
            if(state.password === state.passwordRetype && state.passwordVal){
                state.passwordRetypeVal = true
                state.passwordRetypeValMes = ""
            }else{
                state.passwordRetypeVal = false
                state.passwordRetypeValMes = "password doesn't match"
            }
        },
        setCardNumber: (state, action) => {
            state.cardNumber = action.payload
            if(state.cardNumber.length == 15 || state.cardNumber.length == 16){
                state.cardNumberVal = true
                state.cardNumberValMes = ""
            }else{
                state.cardNumberVal = false
                state.cardNumberValMes = "Please enter a valid card number"
            }
        },
        setSecurityCode: (state, action) => {
            state.securityCode = action.payload
            if(state.securityCode.length == 3){
                state.securityCodeVal = true
                state.securityCodeValMes = ""
            }else{
                state.securityCodeVal = false
                state.securityCodeValMes = "Please enter a valid security code"
            }
        },
        setExpire: (state, action) => {
            state.expire = action.payload
            if(/^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/.test(state.expire)){
                state.expireVal = true
                state.expireValMes = ""
            }else{
                state.expireVal = false
                state.expireValMes = "please enter a valid expiration date"
            }
        },
    }
})
export const inputReducer = inputSlice.reducer;
export const { setEmail, 
    setFname, 
    setLname, 
    setPassword, 
    setPasswordRetype,
    setAddress,
setCardNumber,
setSecurityCode,
setExpire } = inputSlice.actions;
