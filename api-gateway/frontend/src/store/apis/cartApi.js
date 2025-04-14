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

            addToCart: builder.mutation({
                invalidatesTags: ['fetchCartProducts'],
                query: (product) => {
                    return {
                        url: 'cart/add',
                        method: 'post',
                        body: {product},
                        credentials: "include"
                    }
                }
            }),

            productCountDecrement: builder.mutation({
                invalidatesTags: ['fetchCartProducts'],
                query: (incDecRequest) => {
                    return{
                        url: 'cart/product/dec',
                        method: 'post',
                        body: incDecRequest,
                        credentials: "include"

                    }
                }
            }),
            productCountIncrement: builder.mutation({
                invalidatesTags: ['fetchCartProducts'],
                query: (incDecRequest) => {
                    return {
                        url: 'cart/product/inc',
                        method: 'POST', 
                        body: incDecRequest,
                        credentials: "include"

                    }
                }
            }),
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
                useAddToCartMutation,
                useProductCountDecrementMutation,
                useProductCountIncrementMutation,
                useEmptyCartMutation} = cartApi