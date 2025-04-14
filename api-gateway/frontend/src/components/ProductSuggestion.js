import { useFetchProductsQuery } from "../store";
import ProductList from "./ProductList";

export default function ProductSuggestion() {
  const { data } = useFetchProductsQuery();

  return (
    <div className="flex flex-col  justify-start h-full  xl:p-12 ">
      <p className="text-3xl font-black leading-10  w-full text-gray-700 ">
        Suggestions
      </p>
      <ProductList
        products={data}
        suggestion={true}
        className="flex flex-normal overflowx-auto border border-gray-500  bg-black bg-opacity-30 m-1"
      />
    </div>
  );
}
