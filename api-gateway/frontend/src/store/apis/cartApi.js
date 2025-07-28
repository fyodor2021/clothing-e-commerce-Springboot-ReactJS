import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

const cartApi = createApi({
    reducerPath: 'cart',
    baseQuery: fetchBaseQuery({
        baseUrl: '/api',
        prepareHeaders: (headers, { getState }) => {
            const { token} = getState();
            if (token.value) {
                headers.set('Authorization', `Bearer ${token.value}`)
            }
            headers.set('Content-Type','application/json')
            return headers
        },
    }),

    endpoints: (builder) => {
        return {
            emptyCart: builder.mutation({
                query: (email) => {
                    return {
                        url: 'cart/all/'+ email,
                        method: 'PUT',
                        credentials: "include"
                    }
                }
            })
        }
    }
})

export {cartApi}
export const {
                

                useEmptyCartMutation} = cartApi