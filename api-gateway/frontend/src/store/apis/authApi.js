import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const authApi = createApi({
    reducerPath: 'auth',
    baseQuery: fetchBaseQuery({
        baseUrl: '/api/auth',
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
        register: builder.mutation({
            query: (user) => ({
                url: '/register',
                method: 'post',
                body: user,
                credentials: "include"
            })            
        }),
        login: builder.mutation({
            query: (user) => ({
                url: '/authenticate',
                method: 'post',
                body: user,
                credentials: "include"
            })
        }),
        signout: builder.mutation({
            query: (body) => ({
                url: '/signout',
                method: 'delete',
                body,
                credentials: "include"
            })
        }),
        updateUser: builder.mutation({
            invalidatesTags:['loggedUser'],
            query: (user) => ({
                url: '/user/update',
                method: 'put',
                body: user,
                credentials: "include"
            })
        }),
        loggedUser: builder.query({
            providesTags:['loggedUser'],
            query: () => ({
                url:"/user",
                credentials: "include"
            })
        }),
    })
})

export const { useRegisterMutation, useLoginMutation, useLoggedUserQuery, useUpdateUserMutation, useSignoutMutation } = authApi
