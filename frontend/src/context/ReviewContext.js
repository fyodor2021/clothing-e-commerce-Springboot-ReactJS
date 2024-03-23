import { createContext, useState } from "react";
import axios from "axios";
const ReviewContext = createContext();
function ReviewProvider({ children }) {
    const addReview = async (review) => {
        const res = await axios.post('/api/review', 
            review,{
                withCredentials: true
            }
        )
        window.location.reload()
    }
    const valueProvided = {
        addReview
    }
    return <ReviewContext.Provider value={valueProvided}>
                {children}
        </ReviewContext.Provider>
}
export { ReviewProvider };
export default ReviewContext;