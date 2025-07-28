import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";


const orderApi = createApi({
    reducerPath: 'order',
    baseQuery: fetchBaseQuery({
        baseUrl:'/api',
        prepareHeaders: (headers, { getState }) => {
            const { token} = getState();
            if (token.value) {
                headers.set('Authorization', `Bearer ${token.value}`)
            }
            headers.set('Content-Type','application/json')
            return headers
        },
    }),
    endpoints: builder => {
        return {
            placeOrder: builder.mutation({
                invalidatesTags:['userOrders'],
                query: (orderRequest) => {
                    return {
                        url: '/order',
                        method: 'post',
                        body: JSON.stringify(orderRequest),
                        credentials: 'include'
                    }
                }
            }),
            cancelOrder: builder.mutation({
                invalidatesTags: ['userOrders'],
                query: (orderId) => {
                    return {
                        url: `/order/cancel/${orderId}`,
                        method: 'put',
                        credentials: 'include'
                    }
                }
            }),
            getLoggedUserOrders: builder.query({
                providesTags: ['userOrders'],
                query: (loggedUser) => {
                    return {
                        url: `/order/${loggedUser.email}`,
                        method: 'get',
                        credentials: 'include'
                    }
                }
            }),
            getOrderByOrderId: builder.query({
                query: (orderId) => {
                    return {
                        url: `/order/id/${orderId}`,
                        method: 'get',
                        credentials: 'include'
                    }
                }
            })
        }
    }
})


export { orderApi }
export const { usePlaceOrderMutation, useCancelOrderMutation, useLazyGetLoggedUserOrdersQuery, useLazyGetOrderByOrderIdQuery } = orderApi
