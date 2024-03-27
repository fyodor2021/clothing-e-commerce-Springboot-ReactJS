import { createContext, useEffect } from "react";
import axios from 'axios'
import { useState } from 'react'
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import useGeneralContext from "../hooks/useGeneralContext";

const AuthContext = createContext();
function AuthProvider({ children }) {
    const navigate = useNavigate();
    const {setValMessage} = useGeneralContext();
    const [cookie, setCookie, removeCookie] = useCookies(['SESSION']);
    const [loggedUser, setLoggedUser] = useState();
    const [token,setToken] = useState('');    
    const register =(user) => {
        const res = axios.post("api/auth/register", user)
                .then(res => {
                if(res.data){
                    return "User was Successfully Registered"
                }else{
                    return "User Exists"
                }
                })
    }
    const login = (user) => {
        const res = axios.post("/api/auth/authenticate", user)
        .then(res => {
            if(res){
                window.localStorage.setItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL, res.data.token)
                removeCookie('SESSION')
                setToken(window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL))
                window.location.reload();
            }else{
                    setValMessage("User Not Found!")
                }
            }
            
        )
    }
    const signout = () => {
        window.localStorage.removeItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
        window.location.replace('/')
    }
    const valueProvided = { 
        register,login,token,setToken,signout, setLoggedUser,loggedUser
    }
    return <AuthContext.Provider value={valueProvided}>
                {children}
        </AuthContext.Provider>
}
export { AuthProvider };
export default AuthContext;