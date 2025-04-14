import useProductContext from "../hooks/useProductContext";
import Skeleton from "../components/Skeleton";
import ProductList from "../components/ProductList";
import { useLocation } from "react-router-dom";
import {
  useFetchProductsQuery,
} from "../store";
export default function ProductPage() {
  const { data,isLoading } = useFetchProductsQuery();
  const { womenProduct, menProduct, kidsProduct } = useProductContext();
  let skeletons = [];
  for (let i = 0; i < 15; i++) {
    skeletons.push(
      <div key={i} className="m-5">
        <Skeleton />
      </div>
    );
  }
  const { state } = useLocation();
  let filteredProducts;
  if (data) {
    if (state) {
      filteredProducts = data.filter((product) => {
        return (
          product.gender.includes(state.searchTerm) ||
          product.description.includes(state.searchTerm) ||
          product.category.includes(state.searchTerm) ||
          product.tags.includes(state.searchTerm) ||
          product.vendor.includes(state.searchTerm) ||
          product.productName.includes(state.searchTerm) ||
          product.brand.includes(state.searchTerm)
        );
      });
    }
  }
  return (
    <div className="product-page h-screen overflow-y-auto flex justify-end items-end">
      {isLoading ? (
        <div className="cards-wrapper overflow-y-auto h-[calc(100vh-192px)] flex flex-wrap">{skeletons}</div>
      ) : window.location.pathname.includes("women") ? (
        <ProductList products={womenProduct} />
      ) : window.location.pathname.includes("men") ? (
        <ProductList products={menProduct} />
      ) : window.location.pathname.includes("all") ? (
        <ProductList products={data} />
      ) : window.location.pathname.includes("kids") ? (
        <ProductList products={kidsProduct} />
      ) : window.location.pathname.includes("filter") ? (
        filteredProducts ? (
          <ProductList products={filteredProducts} />
        ) : (
          <ProductList products={data} />
        )
      ) : (
        <></>
      )}
    </div>
  );
}
