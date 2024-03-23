import { createContext } from "react";
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
    const token = window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
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
                console.log(res.data.token)
                window.localStorage.setItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL, res.data.token)
                removeCookie('SESSION')
                navigate('/')
            }else{
                    setValMessage("User Not Found!")
                }
            }
            
        )
    }
    const valueProvided = {
        fetchUser,user,register,login,token
    }
    return <AuthContext.Provider value={valueProvided}>
                {children}
        </AuthContext.Provider>
}
export { AuthProvider };
export default AuthContext;