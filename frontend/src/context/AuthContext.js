import { createContext, useEffect } from "react";
import axios from 'axios'
import { useState } from 'react'
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import useGeneralContext from "../hooks/useGeneralContext";

const AuthContext = createContext();
function AuthProvider({ children }) {
    const [user, setUser] = useState();
    const navigate = useNavigate();
    const {valMessage, setValMessage} = useGeneralContext();
    const [cookies, setCookie, removeCookie] = useCookies(['SESSION']);
    const [token,setToken] = useState('');    
    const fetchUser = (userId) => {
        const res = axios.get("api/user/" + userId)
        .then((response) => {
            setUser(response.data)
        })
    }
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
    const valueProvided = {
        fetchUser,user,register,login,token,setToken
    }
    return <AuthContext.Provider value={valueProvided}>
                {children}
        </AuthContext.Provider>
}
export { AuthProvider };
export default AuthContext;