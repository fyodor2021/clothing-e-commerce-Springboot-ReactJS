import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const productApi = createApi({
  reducerPath: "albums",
  baseQuery: fetchBaseQuery({
    baseUrl: "/api",
  }),
  endpoints(builder) {
    return {
      fetchProducts: builder.query({
        query: () => {
          return {
            url: "/product",
            method: "GET",
            credentials: "include",
          };
        },
      }),
      fetchProductDetails: builder.query({
        query: (productId) => {
          return {
            url: `/product/image/${productId}`,
            method: "GET",
            credentials: "include",
          };
        },
      }),
      fetchCartProducts: builder.query({
        providesTags: ["fetchCartProducts"],
        query: (productList) => {
          return {
            url: "/product/list",
            method: "post",
            body: productList,
            credentials: "include",
          };
        },
      }),
    };
  },
});
export const {
  useFetchProductsQuery,
  useFetchProductDetailsQuery,
  useLazyFetchCartProductsQuery,
} = productApi;
