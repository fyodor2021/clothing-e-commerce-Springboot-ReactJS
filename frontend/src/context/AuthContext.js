import { createContext, useEffect } from "react";
import axios from 'axios'
import { useState } from 'react'
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import useValidationContext from "../hooks/useValidationContext";

const AuthContext = createContext();
function AuthProvider({ children }) {
    const navigate = useNavigate();
    const { setValMessage } = useValidationContext();
    const [cookie, setCookie, removeCookie] = useCookies(['SESSION']);
    const [loggedUser, setLoggedUser] = useState();
    const [token, setToken] = useState('');
    const [userChanged, setUserChanged] = useState(false);
    useEffect(() => {
        if (window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)) {
            getLoggedUser()
        }
    }, [userChanged])
    const register = (user) => {
        const res = axios.post("api/auth/register", user)
            .then(res => {
                if (res.data) {
                    setValMessage("User was Successfully Registered")
                    navigate('/login')

                } else {
                    setValMessage("User Exists")
                }
            })
    }
    const login = (user) => {
        const res = axios.post("/api/auth/authenticate", user)
            .then(res => {
                const localItem = {

                }
                if (res) {
                    const d = new Date();
                    d.setHours(d.getHours() + 2)
                    window.localStorage.setItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL, res.data.token)
                    window.localStorage.setItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL_EXPIRATION, d)
                    removeCookie('SESSION')
                    setToken(window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL))
                    window.location.reload();
                } else {
                    setValMessage("User Not Found!")
                }
            }
            )
    }
    const updateUser = async (user, updateInitiator) => {
        await axios.put("/api/auth/user/update", user).then(res => {
            if (updateInitiator && updateInitiator === 'email') {
                signout()
            }
        })
    }
    const getLoggedUser = async () => {
        if (window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)) {
            await axios.get('/api/auth/user').then(res => setLoggedUser(res.data))
        }
    }
    const signout = () => {
        window.localStorage.removeItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
        window.localStorage.removeItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL_EXPIRATION)
        window.location.replace('/')
    }
    const valueProvided = {
        register,
        login,
        token,
        setToken,
        signout,
        setLoggedUser,
        loggedUser,
        updateUser,
        userChanged,
        setUserChanged,
        getLoggedUser
    }
    return <AuthContext.Provider value={valueProvided}>
        {children}
    </AuthContext.Provider>
}
export { AuthProvider };
export default AuthContext;