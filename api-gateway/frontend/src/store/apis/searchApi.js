import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const searchApi = createApi({
    reducerPath: 'search',
    baseQuery: fetchBaseQuery({
        baseUrl: '/api'
    }),
    endpoints: (builder) => ({
        filterProductsBySearchTerm: builder.query({
            query: (searchTerm) => ({
                url: `/product/search/submit/${searchTerm}`,
                method: 'get',
            })
        }),

    })
})

export const { useLazyFilterProductsBySearchTermQuery } = searchApi