import { createContext } from "react";
import axios from 'axios'
import { useState } from 'react'
const UserContext = createContext();
function UserProvider({ children }) {
    const [user, setUser] = useState()
    const fetchUser = (userId) => {
        const res = axios.get("http://localhost:3000/api/user/" + userId)
        .then((response) => {
            setUser(response.data)
        })
    }
    const valueProvided = {
        fetchUser,user
    }
    return <UserContext.Provider value={valueProvided}>
                {children}
        </UserContext.Provider>
}
export { UserProvider };
export default UserContext;