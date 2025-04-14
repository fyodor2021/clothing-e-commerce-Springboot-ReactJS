import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const paymentApi = createApi({
    reducerPath: 'payment',
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
    endpoints: (builder) => ({
        addPaymentMethod: builder.mutation({
            invalidatesTags: ['fetchCardsInfo'],
            query: (addPaymentRequest) => ({
                url: '/wallet/add',
                method: 'post',
                body: JSON.stringify(addPaymentRequest),
                credentials: "include"
            })
        }),
        fetchCardsInfo: builder.query({
            providesTags: ['fetchCardsInfo'],
            query:(email) => {
                return {
                    url: '/wallet/cards/'+email,
                    method: 'get',
                    credentials: "include"
                }
            }
        }),
        DeleteCardInfo: builder.mutation({
            invalidatesTags: ['fetchCardsInfo'],
            query: (walletId) => ({
                url: '/wallet',
                method: 'delete',
                body: JSON.stringify(walletId)
            })
        }),
        

    })
})

export const { useAddPaymentMethodMutation, useFetchCardsInfoQuery, useDeleteCardInfoMutation} = paymentApi